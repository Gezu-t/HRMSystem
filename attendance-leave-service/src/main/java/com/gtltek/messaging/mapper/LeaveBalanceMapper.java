package com.gtltek.messaging.mapper;


import com.gtltek.messaging.dto.leave.LeaveBalanceDTO;
import com.gtltek.messaging.model.LeaveBalance;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeaveBalanceMapper {
    LeaveBalance toEntity(LeaveBalanceDTO dto);
    LeaveBalanceDTO toDTO(LeaveBalance entity);

    void updateEntityFromDto(LeaveBalanceDTO dto, @MappingTarget LeaveBalance entity);
}
