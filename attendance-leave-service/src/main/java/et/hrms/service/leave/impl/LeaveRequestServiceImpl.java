package et.hrms.service.leave.impl;

import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.dal.dto.leave.CreateLeaveRequestDTO;
import et.hrms.dal.dto.leave.LeaveRequestDTO;
import et.hrms.dal.mapper.leave.LeaveRequestMapper;
import et.hrms.dal.model.leave.LeaveRequest;
import et.hrms.dal.model.leave.LeaveRequestEvents;
import et.hrms.dal.model.leave.LeaveRequestStates;
import et.hrms.dal.model.leave.LeaveRequestType;
import et.hrms.dal.repository.leave.LeaveRequestRepository;
import et.hrms.dal.repository.leave.LeaveRequestTypeRepository;
import et.hrms.client.employee.EmployeeClientService;
import et.hrms.service.leave.LeaveRequestService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private static final Logger logger = LoggerFactory.getLogger(LeaveRequestServiceImpl.class);

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeClientService employeeClientService;
    private final LeaveRequestTypeRepository leaveRequestTypeRepository;

    @Override
    public LeaveRequestDTO getLeaveRequestWithEmployee(Long leaveRequestId) {
        logger.info("Fetching leave request with ID: {}", leaveRequestId);

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Leave request not found for ID: " + leaveRequestId));

        EmployeeDTO employee = employeeClientService.getEmployeeById(leaveRequest.getEmployeeId());

        LeaveRequestDTO leaveRequestDTO = LeaveRequestMapper.INSTANCE.leaveRequestToDTO(leaveRequest);
        leaveRequestDTO.setEmployeeId(employee.getId());
        leaveRequestDTO.setEmployeeName(employee.getName());
        leaveRequestDTO.setEmployeeDepartment(employee.getDepartment());

        logger.info("Successfully fetched leave request with employee details.");
        return leaveRequestDTO;
    }

    @Override
    @Transactional
    public LeaveRequestDTO createLeaveRequest(CreateLeaveRequestDTO dto) {
        logger.info("Creating new leave request for employee ID: {}", dto.getEmployeeId());

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
}
