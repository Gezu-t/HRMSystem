package et.hrms.service.leave.impl;

import et.hrms.service.employee.EmployeeClientService;
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
import et.hrms.service.leave.LeaveRequestService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeClientService employeeClientService;
    private final LeaveRequestTypeRepository leaveRequestTypeRepository;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository,
                                   EmployeeClientService employeeClientService,
                                   LeaveRequestTypeRepository leaveRequestTypeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeClientService = employeeClientService;
        this.leaveRequestTypeRepository = leaveRequestTypeRepository;
    }

    @Override
    public LeaveRequestDTO getLeaveRequestWithEmployee(Long leaveRequestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        EmployeeDTO employee = employeeClientService.getEmployeeById(leaveRequest.getEmployeeId());

        LeaveRequestDTO leaveRequestDTO = LeaveRequestMapper.INSTANCE.leaveRequestToDTO(leaveRequest);

        leaveRequestDTO.setEmployeeId(employee.getId());
        leaveRequestDTO.setEmployeeName(employee.getName());
        leaveRequestDTO.setEmployeeDepartment(employee.getDepartment());

        return leaveRequestDTO;
    }

    @Override
    @Transactional
    public LeaveRequestDTO createLeaveRequest(CreateLeaveRequestDTO dto) {
        LeaveRequestType leaveRequestType = leaveRequestTypeRepository.findById(dto.getLeaveTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid leave request type ID"));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());
        leaveRequest.setLeaveType(leaveRequestType);
        leaveRequest.setLeaveRequestReason(dto.getLeaveRequestReason());
        leaveRequest.setEmployeeId(dto.getEmployeeId());
        leaveRequest.setTaskId(dto.getTaskId());
        leaveRequest.setProjectId(dto.getProjectId());
        leaveRequest.setStatus(true);
        leaveRequest.setLeaveRequestEvents(LeaveRequestEvents.SUBMITTED);
        leaveRequest.setLeaveRequestStates(LeaveRequestStates.PENDING_APPROVAL);

        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);
        return LeaveRequestMapper.INSTANCE.leaveRequestToDTO(savedLeaveRequest);
    }
}
