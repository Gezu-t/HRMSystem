package et.hrms.dal.mapper.leave;

import et.hrms.dal.dto.leave.LeaveRequestDTO;
import et.hrms.dal.model.leave.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring")
public interface LeaveRequestMapper {
    LeaveRequestMapper INSTANCE = Mappers.getMapper(LeaveRequestMapper.class);

    LeaveRequestDTO leaveRequestToDTO(LeaveRequest leaveRequest);
}
