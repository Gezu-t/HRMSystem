package et.hrms.dal.mapper.attendance;

import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.dal.model.attendance.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

  AttendanceMapper  INSTANCE = Mappers.getMapper(AttendanceMapper.class);

  AttendanceDTO toDto(Attendance attendance);

  Attendance toEntity(AttendanceDTO attendanceDTO);


}
