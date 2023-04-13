package et.hrms.dal.mapping;

import et.hrms.dal.dto.AttendanceDTO;
import et.hrms.dal.model.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

  AttendanceMapper  INSTANCE = Mappers.getMapper(AttendanceMapper.class);

  @Mapping(source = "employee.id", target = "employeeId")
  AttendanceDTO toDto(Attendance attendance);

  @Mapping(source = "employeeId", target = "employee.id")
  Attendance toEntity(AttendanceDTO attendanceDTO);


}
