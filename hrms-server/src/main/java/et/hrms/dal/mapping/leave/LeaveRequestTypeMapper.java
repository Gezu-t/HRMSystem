package et.hrms.dal.mapping.leave;

import et.hrms.dal.dto.leave.LeaveRequestTypeDTO;
import et.hrms.dal.model.leave.LeaveRequestType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveRequestTypeMapper {

  LeaveRequestTypeMapper INSTANCE = Mappers.getMapper(LeaveRequestTypeMapper.class);

  LeaveRequestType toEntity(LeaveRequestTypeDTO leaveRequestTypeDTO);
  LeaveRequestTypeDTO toDTO(LeaveRequestType leaveRequestType);

}
