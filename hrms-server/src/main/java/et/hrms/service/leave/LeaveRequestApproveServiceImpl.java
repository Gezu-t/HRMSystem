package et.hrms.service.leave;

import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;
import et.hrms.dal.mapping.leave.LeaveRequestApproveMapper;
import et.hrms.dal.model.employee.Employee;
import et.hrms.dal.model.leave.LeaveRequest;
import et.hrms.dal.model.leave.LeaveRequestApprove;
import et.hrms.dal.repository.employee.EmployeeRepository;
import et.hrms.dal.repository.leave.LeaveRequestApproveRepository;
import et.hrms.dal.repository.leave.LeaveRequestRepository;
import et.hrms.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LeaveRequestApproveServiceImpl implements LeaveRequestApproveService {

  private final LeaveRequestApproveRepository leaveRequestApproveRepository;
  private final LeaveRequestRepository leaveRequestRepository;
  private final EmployeeRepository employeeRepository;
  private final LeaveRequestApproveMapper leaveRequestApproveMapper;

  /**
   *
   * @param leaveRequestId
   * @param leaveRequestApproveDTO
   */
  @Override
  public void creatLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO) {
    LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
            .orElseThrow(() -> new EntityNotFoundException("Leave request is not found by this ID: " + leaveRequestId));

    Employee approvedBy = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee is not found by this ID: " + employeeId));

    LeaveRequestApprove leaveRequestApprove = leaveRequestApproveMapper.toEntity(leaveRequestApproveDTO);
    leaveRequestApprove.setLeaveRequest(leaveRequest);
    leaveRequestApprove.setApprovedBy(approvedBy);
    leaveRequestApproveRepository.save(leaveRequestApprove);
  }


  /**
   * @param leaveRequestApproveDTO
   * @return
   */
  @Override
  public LeaveRequestApproveDTO updateLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO) {
    LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
            .orElseThrow(() -> new EntityNotFoundException("Leave request is not found by this ID: " + leaveRequestId));

    Employee approvedBy = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee is not found by this ID: " + employeeId));

    Optional<LeaveRequestApprove> leaveRequestApproveOptional = leaveRequestApproveRepository.findById(leaveRequestApproveDTO.getLeaveRequestApproveId());

    if (leaveRequestApproveOptional.isPresent()) {
      LeaveRequestApprove leaveRequestApprove = leaveRequestApproveMapper.toEntity(leaveRequestApproveDTO);
      leaveRequestApprove.setLeaveRequest(leaveRequest);
      leaveRequestApprove.setApprovedBy(approvedBy);

      leaveRequestApproveRepository.save(leaveRequestApprove);
      return leaveRequestApproveDTO;
    } else {
      throw new EntityNotFoundException("Leave request approval is not found by this ID: " + leaveRequestApproveDTO.getLeaveRequestApproveId());
    }
  }

  @Override
  public LeaveRequestApproveDTO findById(Long leaveRequestApproveId) {
    LeaveRequestApprove leaveRequestApprove = leaveRequestApproveRepository.findById(leaveRequestApproveId)
            .orElseThrow(() -> new EntityNotFoundException("Leave request approval is not found by this ID: " + leaveRequestApproveId));

    return leaveRequestApproveMapper.toDTO(leaveRequestApprove);
  }

  @Override
  public List<LeaveRequestApproveDTO> findAll() {
    List<LeaveRequestApprove> leaveRequestApproveList = leaveRequestApproveRepository.findAll();
    return leaveRequestApproveList.stream()
            .map(leaveRequestApproveMapper::toDTO)
            .collect(Collectors.toList());
  }
}
