package et.hrms.dal.mapper;

import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.dal.model.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

  AttendanceMapper  INSTANCE = Mappers.getMapper(AttendanceMapper.class);

  AttendanceDTO toDto(Attendance attendance);

  Attendance toEntity(AttendanceDTO attendanceDTO);


}
