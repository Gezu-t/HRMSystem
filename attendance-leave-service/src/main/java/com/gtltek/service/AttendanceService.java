package com.gtltek.service;

import com.gtltek.messaging.dto.attendance.AttendanceDTO;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public interface AttendanceService {
    AttendanceDTO saveAttendance(Long employeeId, AttendanceDTO attendanceDTO);

    AttendanceDTO getAttendanceById(Long id);

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
