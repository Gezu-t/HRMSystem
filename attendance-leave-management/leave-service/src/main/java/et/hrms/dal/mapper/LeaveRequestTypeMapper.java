package et.hrms.dal.mapper;

import et.hrms.dal.dto.leave.LeaveRequestTypeDTO;
import et.hrms.dal.model.LeaveRequestType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeaveRequestTypeMapper {
    LeaveRequestTypeMapper INSTANCE = Mappers.getMapper(LeaveRequestTypeMapper.class);

    LeaveRequestTypeDTO leaveRequestTypeToDTO(LeaveRequestType leaveRequestType);
}

