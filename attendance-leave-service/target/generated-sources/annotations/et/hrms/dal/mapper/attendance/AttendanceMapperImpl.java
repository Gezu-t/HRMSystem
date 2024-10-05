package et.hrms.dal.mapper.attendance;

import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.dal.model.attendance.Attendance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:42+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public AttendanceDTO toDto(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        AttendanceDTO attendanceDTO = new AttendanceDTO();

        attendanceDTO.setAbsenteeReportedBy( attendance.getAbsenteeReportedBy() );
        attendanceDTO.setAbsenteeDate( attendance.getAbsenteeDate() );
        attendanceDTO.setAbsenteeDescription( attendance.getAbsenteeDescription() );
        attendanceDTO.setRecordedBy( attendance.getRecordedBy() );
        attendanceDTO.setRecordedDate( attendance.getRecordedDate() );
        attendanceDTO.setAttendanceState( attendance.getAttendanceState() );
        attendanceDTO.setEmployeeId( attendance.getEmployeeId() );
        attendanceDTO.setAttendanceStatus( attendance.getAttendanceStatus() );
        attendanceDTO.setForDepartment( attendance.getForDepartment() );

        return attendanceDTO;
    }

    @Override
    public Attendance toEntity(AttendanceDTO attendanceDTO) {
        if ( attendanceDTO == null ) {
            return null;
        }

        Attendance attendance = new Attendance();

        attendance.setAbsenteeReportedBy( attendanceDTO.getAbsenteeReportedBy() );
        attendance.setAbsenteeDate( attendanceDTO.getAbsenteeDate() );
        attendance.setAbsenteeDescription( attendanceDTO.getAbsenteeDescription() );
        attendance.setRecordedBy( attendanceDTO.getRecordedBy() );
        attendance.setRecordedDate( attendanceDTO.getRecordedDate() );
        attendance.setAttendanceState( attendanceDTO.getAttendanceState() );
        attendance.setForDepartment( attendanceDTO.getForDepartment() );
        attendance.setAttendanceStatus( attendanceDTO.getAttendanceStatus() );
        attendance.setEmployeeId( attendanceDTO.getEmployeeId() );

        return attendance;
    }
}
