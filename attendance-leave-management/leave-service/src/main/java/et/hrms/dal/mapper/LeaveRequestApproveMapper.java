package et.hrms.dal.mapper;

import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;
import et.hrms.dal.model.LeaveRequestApprove;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveRequestApproveMapper {

    LeaveRequestApproveMapper INSTANCE = Mappers.getMapper(LeaveRequestApproveMapper.class);

    LeaveRequestApproveDTO toDTO(LeaveRequestApprove leaveRequestApprove);
    LeaveRequestApprove toEntity(LeaveRequestApproveDTO leaveRequestApproveDTO);


}