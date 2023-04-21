package et.hrms.dal.mapping.leave;

import et.hrms.dal.dto.leave.LeaveRequestDTO;
import et.hrms.dal.model.leave.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaveRequestMapper {

  LeaveRequestMapper INSTANCE = Mappers.getMapper(LeaveRequestMapper.class);

  LeaveRequest toEntity(LeaveRequestDTO leaveRequestDTO);
  LeaveRequestDTO toDTO(LeaveRequest leaveRequest);

  List<LeaveRequest> toEntityList(List<LeaveRequestDTO> leaveRequestDTO);
  List<LeaveRequestDTO> toDTOList(List<LeaveRequest> leaveRequest);


}
