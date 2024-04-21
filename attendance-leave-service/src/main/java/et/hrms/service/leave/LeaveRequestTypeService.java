package et.hrms.service.leave;

import et.hrms.dal.dto.leave.LeaveRequestTypeDTO;
import et.hrms.dal.model.leave.LeaveRequestType;

import java.util.List;

public interface LeaveRequestTypeService {
    LeaveRequestTypeDTO getLeaveRequestTypeById(Long typeId);

    List<LeaveRequestTypeDTO> getAllLeaveRequestTypeDTOs();
}
