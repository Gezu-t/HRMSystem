package com.gtltek.messaging.mapper;

import com.gtltek.messaging.dto.leave.LeaveRequestApproveDTO;
import com.gtltek.messaging.model.LeaveRequestApprove;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveRequestApproveMapper {

    LeaveRequestApproveMapper INSTANCE = Mappers.getMapper(LeaveRequestApproveMapper.class);

    LeaveRequestApproveDTO toDTO(LeaveRequestApprove leaveRequestApprove);
    LeaveRequestApprove toEntity(LeaveRequestApproveDTO leaveRequestApproveDTO);


}