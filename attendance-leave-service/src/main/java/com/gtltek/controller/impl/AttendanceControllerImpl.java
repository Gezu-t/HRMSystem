package com.gtltek.controller.impl;

import com.gtltek.controller.AttendanceController;
import com.gtltek.messaging.dto.attendance.AttendanceDTO;
import com.gtltek.exception.CustomEntityNotFoundException;
import com.gtltek.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceControllerImpl implements AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/{employeeId}")
    public ResponseEntity<AttendanceDTO> createAttendance(@PathVariable Long employeeId, @Valid @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO createdAttendance = attendanceService.saveAttendance(employeeId, attendanceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttendance);
    }

    @GetMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable Long attendanceId) {
        return ResponseEntity.ok(attendanceService.getAttendanceById(attendanceId));
    }

    @PutMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable Long attendanceId, @Valid @RequestBody AttendanceDTO attendanceDTO) {
        return ResponseEntity.ok(attendanceService.updateAttendance(attendanceId, attendanceDTO));
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        return ResponseEntity.ok(attendanceService.getAllAttendance(page, size, Sort.by(sort)));
    }

    @GetMapping("/monthly-report/{employeeId}")
    public ResponseEntity<List<AttendanceDTO>> getMonthlyAttendanceReport(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) YearMonth month) {
        return ResponseEntity.ok(attendanceService.getMonthlyAttendanceReport(employeeId, month));
    }

    @GetMapping("/department-summary")
    public ResponseEntity<Map<String, List<AttendanceDTO>>> getAttendanceSummaryByDepartment(
            @RequestParam String department,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(attendanceService.getAttendanceSummaryByDepartment(department, startDate, endDate));
    }

    @PostMapping("/start/{employeeId}")
    public ResponseEntity<AttendanceDTO> markAttendanceStart(@PathVariable Long employeeId) {
        return ResponseEntity.ok(attendanceService.markAttendanceStart(employeeId));
    }

    @PostMapping("/end/{employeeId}")
    public ResponseEntity<AttendanceDTO> markAttendanceEnd(@PathVariable Long employeeId) {
        return ResponseEntity.ok(attendanceService.markAttendanceEnd(employeeId));
    }

    @GetMapping("/working-hours")
    public ResponseEntity<Double> calculateTotalWorkingHours(
            @RequestParam Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(attendanceService.calculateTotalWorkingHours(employeeId, from, to));
    }

    @GetMapping("/compliance-report")
    public ResponseEntity<List<AttendanceDTO>> generateComplianceReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(attendanceService.generateComplianceReport(startDate, endDate));
    }

    @ExceptionHandler(CustomEntityNotFoundException.class)
    public ResponseEntity<String> handleCustomEntityNotFoundException(CustomEntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}