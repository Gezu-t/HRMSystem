<<<<<<<< HEAD:hrms-server/src/main/java/et/hrms/service/project/AttendanceService.java
package et.hrms.service.project;
========
package et.hrms.service.attendance;
>>>>>>>> hrms-001:attendance-leave-service/src/main/java/et/hrms/service/attendance/AttendanceService.java

import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.dal.dto.employee.EmployeeDTO;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public interface AttendanceService {
  AttendanceDTO saveAttendance(Long employeeId, AttendanceDTO attendanceDTO);

  AttendanceDTO getAttendanceById(Long id);

    AttendanceDTO getAttendanceByEmployeeName(EmployeeDTO employeeDTO);

    AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO);

  List<AttendanceDTO> getAllAttendance(int page, int size, Sort sort);

  List<AttendanceDTO> getMonthlyAttendanceReport(Long employeeId, YearMonth month);

  Map<String, List<AttendanceDTO>> getAttendanceSummaryByDepartment(String departmentName, LocalDate startDate, LocalDate endDate);

  AttendanceDTO markAttendanceStart(Long employeeId);

  AttendanceDTO markAttendanceEnd(Long employeeId);

  List<AttendanceDTO> getAbsenteeismTrends(Long employeeId);

  List<AttendanceDTO> generateComplianceReport(LocalDate startDate, LocalDate endDate);

  Double calculateTotalWorkingHours(Long employeeId, LocalDate from, LocalDate to);
}
