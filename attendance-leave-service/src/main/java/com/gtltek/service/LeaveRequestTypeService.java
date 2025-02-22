package com.gtltek.service;

import com.gtltek.messaging.dto.leave.LeaveRequestTypeDTO;

import java.util.List;

public interface LeaveRequestTypeService {
    LeaveRequestTypeDTO getLeaveRequestTypeById(Long typeId);

    List<LeaveRequestTypeDTO> getAllLeaveRequestTypeDTOs();
}
