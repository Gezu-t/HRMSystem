package et.hrms.service.leave;

import et.hrms.dal.dto.leave.LeaveRequestTypeDTO;

import java.util.List;

public interface LeaveRequestTypeService {
  public void createLeaveRequestType(LeaveRequestTypeDTO leaveRequestTypeDTO);
  public LeaveRequestTypeDTO updateLeaveRequestType(LeaveRequestTypeDTO leaveRequestTypeDTO);
  public LeaveRequestTypeDTO getLeaveRequestTypeId(Long leaveRequestTypeId);
  public List<LeaveRequestTypeDTO> getAllLeaveRequestType();

}
