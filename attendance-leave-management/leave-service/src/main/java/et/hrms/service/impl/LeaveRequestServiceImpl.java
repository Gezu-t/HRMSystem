package et.hrms.service.impl;

import et.hrms.client.employee.EmployeeClientService;
import et.hrms.dal.dto.employee.EmployeeLeaveDTO;
import et.hrms.dal.dto.leave.CreateLeaveRequestDTO;
import et.hrms.dal.dto.leave.LeaveRequestDTO;
import et.hrms.dal.mapper.LeaveRequestMapper;
import et.hrms.dal.model.LeaveRequest;
import et.hrms.dal.model.LeaveRequestEvents;
import et.hrms.dal.model.LeaveRequestStates;
import et.hrms.dal.model.LeaveRequestType;
import et.hrms.dal.repository.LeaveRequestRepository;
import et.hrms.dal.repository.LeaveRequestTypeRepository;
import et.hrms.service.LeaveRequestService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private static final Logger logger = LoggerFactory.getLogger(LeaveRequestServiceImpl.class);

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeClientService employeeClientService;
    private final LeaveRequestTypeRepository leaveRequestTypeRepository;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository, EmployeeClientService employeeClientService, LeaveRequestTypeRepository leaveRequestTypeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeClientService = employeeClientService;
        this.leaveRequestTypeRepository = leaveRequestTypeRepository;
    }

    @Override
    public LeaveRequestDTO getLeaveRequestWithEmployee(Long leaveRequestId) {
        logger.info("Fetching leave request with ID: {}", leaveRequestId);

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Leave request not found for ID: " + leaveRequestId));

        EmployeeLeaveDTO employee = employeeClientService.getEmployeeById(leaveRequest.getEmployeeId());

        LeaveRequestDTO leaveRequestDTO = LeaveRequestMapper.INSTANCE.leaveRequestToDTO(leaveRequest);
        leaveRequestDTO.setEmployeeId(employee.getId());
        leaveRequestDTO.setDepartmentId(employee.getDepartmentId());

        logger.info("Successfully fetched leave request with employeeprofile details.");
        return leaveRequestDTO;
    }

    @Override
    @Transactional
    public LeaveRequestDTO createLeaveRequest(CreateLeaveRequestDTO dto) {
        logger.info("Creating new leave request for employeeprofile ID: {}", dto.getEmployeeId());

        LeaveRequestType leaveRequestType = leaveRequestTypeRepository.findById(dto.getLeaveTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid leave request type ID: " + dto.getLeaveTypeId()));

        var leaveRequest = new LeaveRequest();
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());
        leaveRequest.setLeaveType(leaveRequestType);
        leaveRequest.setLeaveRequestReason(dto.getLeaveRequestReason());
        leaveRequest.setEmployeeId(dto.getEmployeeId());
        leaveRequest.setTaskId(dto.getTaskId());
        leaveRequest.setStatus(true);
        leaveRequest.setLeaveRequestEvents(LeaveRequestEvents.SUBMITTED);
        leaveRequest.setLeaveRequestStates(LeaveRequestStates.PENDING_APPROVAL);

        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);
        LeaveRequestDTO savedLeaveRequestDTO = LeaveRequestMapper.INSTANCE.leaveRequestToDTO(savedLeaveRequest);

        logger.info("Successfully created leave request with ID: {}", savedLeaveRequest.getId());
        return savedLeaveRequestDTO;
    }

    /**
     * Handles leave requests for an employee based on the employee's ID.
     *
     * @param employeeId The ID of the employee to search for and process a leave request.
     * @return Optional<EmployeeLeaveDTO> The employee details if found and processed.
     * @throws IllegalArgumentException if no employee is found or the employee ID is not provided.
     */
    @Override
    public Optional<EmployeeLeaveDTO> handleLeaveRequestForEmployee(Long employeeId) {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee ID must not be empty or invalid");
        }

        // Assuming this fetches a single employee by ID
        EmployeeLeaveDTO employee = employeeClientService.getEmployeeById(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("No employee found with the ID: " + employeeId);
        }

        // Further processing can go here, like creating a leave request in the database, etc.
        return Optional.of(employee);
    }



}
