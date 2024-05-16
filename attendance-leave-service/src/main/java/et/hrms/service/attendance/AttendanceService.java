<<<<<<<< HEAD:hrms-server/src/main/java/et/hrms/service/project/AttendanceService.java
package et.hrms.service.project;
========
package et.hrms.service.attendance;
>>>>>>>> hrms-001:attendance-leave-service/src/main/java/et/hrms/service/attendance/AttendanceService.java

import et.hrms.dal.dto.attendance.AttendanceDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AttendanceService {
  AttendanceDTO saveAttendance(Long employeeId, AttendanceDTO attendanceDTO);

  AttendanceDTO getAttendanceById(Long id);

  AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO);

  List<AttendanceDTO> getAllAttendance(int page, int size, Sort sort);
}
