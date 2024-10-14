package et.hrms.service;

import et.hrms.dal.dto.leave.LeaveRequestTypeDTO;

import java.util.List;

public interface LeaveRequestTypeService {
    LeaveRequestTypeDTO getLeaveRequestTypeById(Long typeId);

    List<LeaveRequestTypeDTO> getAllLeaveRequestTypeDTOs();
}
