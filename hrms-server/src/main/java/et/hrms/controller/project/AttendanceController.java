package et.hrms.controller.project;

import et.hrms.dal.dto.attendance.AttendanceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AttendanceController {
  ResponseEntity<AttendanceDTO> createAttendance(@PathVariable("employeeId") Long employeeId,
                                                 @RequestBody AttendanceDTO attendanceDTO);
  ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable("attendanceId") Long attendanceId);
  ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable("attendanceId") Long attendanceId,
                                                 @RequestBody AttendanceDTO attendanceDTO);
  ResponseEntity<List<AttendanceDTO>> getAllAttendance(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id, Asc") String[] sort);
}
