package et.hrms.service;

import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;

import java.util.List;

public interface LeaveRequestApproveService {

  void createLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO);

  LeaveRequestApproveDTO updateLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO);

  LeaveRequestApproveDTO findById(Long leaveRequestApproveId);

  List<LeaveRequestApproveDTO> findAll();
}
