package et.hrms.dal.mapper;


import et.hrms.dal.dto.leave.LeaveBalanceDTO;
import et.hrms.dal.model.LeaveBalance;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeaveBalanceMapper {
    LeaveBalance toEntity(LeaveBalanceDTO dto);
    LeaveBalanceDTO toDTO(LeaveBalance entity);

    void updateEntityFromDto(LeaveBalanceDTO dto, @MappingTarget LeaveBalance entity);
}
