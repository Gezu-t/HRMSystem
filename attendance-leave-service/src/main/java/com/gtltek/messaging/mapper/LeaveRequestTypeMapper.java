package com.gtltek.messaging.mapper;

import com.gtltek.messaging.dto.leave.LeaveRequestTypeDTO;
import com.gtltek.messaging.model.LeaveRequestType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeaveRequestTypeMapper {
    LeaveRequestTypeMapper INSTANCE = Mappers.getMapper(LeaveRequestTypeMapper.class);

    LeaveRequestTypeDTO leaveRequestTypeToDTO(LeaveRequestType leaveRequestType);
}

