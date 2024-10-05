package et.hrms.service.leave.impl;

import dal.dto.employee.EmployeeDTO;
import et.hrms.client.employee.EmployeeClientService;
import et.hrms.dal.dto.leave.CreateLeaveRequestDTO;
import et.hrms.dal.dto.leave.LeaveRequestDTO;
import et.hrms.dal.mapper.leave.LeaveRequestMapper;
import et.hrms.dal.model.leave.LeaveRequest;
import et.hrms.dal.model.leave.LeaveRequestEvents;
import et.hrms.dal.model.leave.LeaveRequestStates;
import et.hrms.dal.model.leave.LeaveRequestType;
import et.hrms.dal.repository.leave.LeaveRequestRepository;
import et.hrms.dal.repository.leave.LeaveRequestTypeRepository;
import et.hrms.service.leave.LeaveRequestService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;
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

        EmployeeDTO employee = employeeClientService.getEmployeeById(leaveRequest.getEmployeeId());

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
     * Handles leave requests for an employeeprofile based on the employeeprofile's name.
     *
     * @param employeeName The name of the employeeprofile to search for and process a leave request.
     * @return Optional<EmployeeDTO> The employeeprofile details if found and processed.
     * @throws IllegalArgumentException if no employeeprofile is found or the employeeprofile name is not provided.
     */
    @Override
    public Optional<EmployeeDTO> handleLeaveRequestForEmployee(String employeeName) {
        Assert.hasText(employeeName, "Employee name must not be empty");

        EmployeeDTO[] foundEmployees = employeeClientService.searchEmployeesByName(employeeName);
        if (foundEmployees.length == 0) {
            throw new IllegalArgumentException("No employeeprofile found with the name: " + employeeName);
        }

        // Assuming we need to process the leave request for the first found employeeprofile
        EmployeeDTO employeeToProcess = Arrays.stream(foundEmployees).findFirst()
                .orElseThrow(() -> new IllegalStateException("Unexpected error processing employeeprofile data"));

        // Further processing can go here, like creating a leave request in the database, etc.
        // Assuming this method would be void if no further return is needed or it could return some result type
        return Optional.of(employeeToProcess);
    }


//    public CompletableFuture<EmployeeDTO> handleLeaveRequestForEmployeeAsync(String employeeName) {
//        return CompletableFuture.supplyAsync(() -> {
//            EmployeeDTO[] foundEmployees = employeeClientService.searchEmployeesByName(employeeName);
//            if (foundEmployees.length == 0) {
//                throw new RuntimeException("No employeeprofile found with the name: " + employeeName);
//            }
//            // For simplicity, assume processing the first found employeeprofile
//            return foundEmployees[0];
//        });
//    }

}
