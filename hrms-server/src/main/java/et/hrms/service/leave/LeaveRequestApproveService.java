package et.hrms.service.leave;

import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;

import java.util.List;

public interface LeaveRequestApproveService {


  void creatLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO);
  LeaveRequestApproveDTO updateLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO);

  LeaveRequestApproveDTO findById(Long leaveRequestApproveId);

  List<LeaveRequestApproveDTO> findAll();
}
