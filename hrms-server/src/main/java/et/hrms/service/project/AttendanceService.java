package et.hrms.service.project;

import et.hrms.dal.dto.attendance.AttendanceDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AttendanceService {
  AttendanceDTO saveAttendance(Long employeeId, AttendanceDTO attendanceDTO);

  AttendanceDTO getAttendanceById(Long id);

  AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO);

  List<AttendanceDTO> getAllAttendance(int page, int size, Sort sort);
}
