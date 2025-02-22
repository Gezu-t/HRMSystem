package com.gtltek.messaging.mapper;

import com.gtltek.messaging.dto.attendance.AttendanceDTO;
import com.gtltek.messaging.model.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

  AttendanceMapper  INSTANCE = Mappers.getMapper(AttendanceMapper.class);

  AttendanceDTO toDto(Attendance attendance);

  Attendance toEntity(AttendanceDTO attendanceDTO);


}
