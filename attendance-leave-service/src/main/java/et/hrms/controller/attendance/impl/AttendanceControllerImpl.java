package et.hrms.controller.attendance.impl;

import et.hrms.controller.attendance.AttendanceController;
import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.service.attendance.AttendanceService;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceControllerImpl implements AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceControllerImpl(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<AttendanceDTO> createAttendance(@PathVariable("employeeId") Long employeeId, @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO dto = attendanceService.saveAttendance(employeeId, attendanceDTO);
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable("attendanceId") Long attendanceId) {
        AttendanceDTO attendanceDTO = attendanceService.getAttendanceById(attendanceId);
        if (attendanceDTO != null) {
            return ResponseEntity.ok(attendanceDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable("attendanceId") Long attendanceId, @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO updatedAttendanceDTO = attendanceService.updateAttendance(attendanceId, attendanceDTO);
        if (updatedAttendanceDTO != null) {
            return ResponseEntity.ok(updatedAttendanceDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance(@RequestParam("page") int page, @RequestParam("size") int size,
                                                                @RequestParam("sort") String[] sort) {
        return ResponseEntity.ok(attendanceService.getAllAttendance(page, size, Sort.by(sort)));
    }

    @GetMapping("/monthly-report/{employeeId}")
    public ResponseEntity<List<AttendanceDTO>> getMonthlyAttendanceReport(@PathVariable("employeeId") Long employeeId,
                                                                          @RequestParam("month") YearMonth month) {
        List<AttendanceDTO> attendanceDTOS = attendanceService.getMonthlyAttendanceReport(employeeId, month);
        return ResponseEntity.ok(attendanceDTOS);
    }

    @GetMapping("/department-summary")
    public ResponseEntity<Map<String, List<AttendanceDTO>>> getAttendanceSummaryByDepartment(
            @RequestParam("department") String department,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Map<String, List<AttendanceDTO>> summary = attendanceService.getAttendanceSummaryByDepartment(department, startDate, endDate);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/start/{employeeId}")
    public ResponseEntity<AttendanceDTO> markAttendanceStart(@PathVariable("employeeId") Long employeeId) {
        AttendanceDTO attendanceDTO = attendanceService.markAttendanceStart(employeeId);
        return ResponseEntity.ok(attendanceDTO);
    }

    @PostMapping("/end/{employeeId}")
    public ResponseEntity<AttendanceDTO> markAttendanceEnd(@PathVariable("employeeId") Long employeeId) {
        AttendanceDTO attendanceDTO = attendanceService.markAttendanceEnd(employeeId);
        return ResponseEntity.ok(attendanceDTO);
    }

    @GetMapping("/working-hours")
    public ResponseEntity<Double> calculateTotalWorkingHours(@RequestParam("employeeId") Long employeeId,
                                                             @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                             @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        Double totalHours = attendanceService.calculateTotalWorkingHours(employeeId, from, to);
        return ResponseEntity.ok(totalHours);
    }

    @GetMapping("/compliance-report")
    public ResponseEntity<List<AttendanceDTO>> generateComplianceReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<AttendanceDTO> report = attendanceService.generateComplianceReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
