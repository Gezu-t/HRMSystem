package et.hrms.service.leave;

import et.hrms.dal.dto.leave.LeaveRequestDTO;

import java.util.List;

public interface LeaveRequestService {


  void createLeaveRequest(Long employeeId, LeaveRequestDTO leaveRequestDTO);

  void updateLeaveRequest(Long employeeId, LeaveRequestDTO leaveRequestDTO);

  LeaveRequestDTO getLeaveRequestByEmployeeId(Long employeeId);

  List<LeaveRequestDTO> getLeaveRequestByStatusAndEmployeeId(Long employeeId, boolean status);

  List<LeaveRequestDTO> getAllLeaveRequest(Long employeeId);
}
