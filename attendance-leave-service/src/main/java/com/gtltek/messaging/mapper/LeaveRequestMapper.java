package com.gtltek.messaging.mapper;

import com.gtltek.messaging.dto.leave.LeaveRequestDTO;
import com.gtltek.messaging.model.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring")
public interface LeaveRequestMapper {
    LeaveRequestMapper INSTANCE = Mappers.getMapper(LeaveRequestMapper.class);

    LeaveRequestDTO leaveRequestToDTO(LeaveRequest leaveRequest);
}
