package et.hrms.service.leave.impl;

import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;
import et.hrms.dal.mapper.leave.LeaveRequestApproveMapper;
import et.hrms.dal.model.leave.LeaveRequest;
import et.hrms.dal.model.leave.LeaveRequestApprove;
import et.hrms.dal.repository.leave.LeaveRequestApproveRepository;
import et.hrms.dal.repository.leave.LeaveRequestRepository;
import et.hrms.service.employee.EmployeeClientService;
import et.hrms.service.leave.LeaveRequestApproveService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeaveRequestApproveServiceImpl implements LeaveRequestApproveService {

  private final LeaveRequestApproveRepository leaveRequestApproveRepository;
  private final LeaveRequestRepository leaveRequestRepository;
  private final EmployeeClientService employeeClientService;
  private final LeaveRequestApproveMapper leaveRequestApproveMapper;

  public LeaveRequestApproveServiceImpl(LeaveRequestApproveRepository leaveRequestApproveRepository,
                                        LeaveRequestRepository leaveRequestRepository,
                                        EmployeeClientService employeeClientService,
                                        LeaveRequestApproveMapper leaveRequestApproveMapper) {
    this.leaveRequestApproveRepository = leaveRequestApproveRepository;
    this.leaveRequestRepository = leaveRequestRepository;
    this.employeeClientService = employeeClientService;
    this.leaveRequestApproveMapper = leaveRequestApproveMapper;
  }

  @Override
  public void createLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO) {
    LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
            .orElseThrow(() -> new RuntimeException("Leave request not found: " + leaveRequestId));

    EmployeeDTO approvedBy = employeeClientService.getEmployeeById(employeeId);

    LeaveRequestApprove leaveRequestApprove = leaveRequestApproveMapper.toEntity(leaveRequestApproveDTO);
    leaveRequestApprove.setLeaveRequest(leaveRequest);
    leaveRequestApprove.setApprovedByEmployeeId(approvedBy.getId());

    leaveRequestApproveRepository.save(leaveRequestApprove);
  }

  @Override
  public LeaveRequestApproveDTO updateLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO) {
    LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
            .orElseThrow(() -> new RuntimeException("Leave request not found: " + leaveRequestId));

    EmployeeDTO approvedBy = employeeClientService.getEmployeeById(employeeId);

    LeaveRequestApprove leaveRequestApprove = leaveRequestApproveRepository.findById(leaveRequestApproveDTO.getLeaveRequestApproveId())
            .orElseThrow(() -> new RuntimeException("Leave request approval not found: " + leaveRequestApproveDTO.getLeaveRequestApproveId()));

    leaveRequestApprove.setLeaveRequest(leaveRequest);
    leaveRequestApprove.setApprovedByEmployeeId(approvedBy.getId());

    leaveRequestApproveRepository.save(leaveRequestApprove);

    return leaveRequestApproveMapper.toDTO(leaveRequestApprove);
  }

  @Override
  public LeaveRequestApproveDTO findById(Long leaveRequestApproveId) {
    LeaveRequestApprove leaveRequestApprove = leaveRequestApproveRepository.findById(leaveRequestApproveId)
            .orElseThrow(() -> new RuntimeException("Leave request approval not found: " + leaveRequestApproveId));

    return leaveRequestApproveMapper.toDTO(leaveRequestApprove);
  }

  @Override
  public List<LeaveRequestApproveDTO> findAll() {
    List<LeaveRequestApprove> leaveRequestApproves = leaveRequestApproveRepository.findAll();
    return leaveRequestApproves.stream()
            .map(leaveRequestApproveMapper::toDTO)
            .collect(Collectors.toList());
  }
}
