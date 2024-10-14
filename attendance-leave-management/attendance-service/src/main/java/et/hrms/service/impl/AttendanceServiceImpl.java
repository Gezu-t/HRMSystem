package et.hrms.service.impl;

import dal.dto.employee.EmployeeDTO;
import et.hrms.client.employee.EmployeeClientService;
import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.dal.mapper.AttendanceMapper;
import et.hrms.dal.model.Attendance;
import et.hrms.dal.repository.AttendanceRepository;
import et.hrms.exception.CustomEntityNotFoundException;
import et.hrms.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final EmployeeClientService employeeClientService;

    @Override
    @CacheEvict(value = "attendance", key = "#result.id")
    public AttendanceDTO saveAttendance(Long employeeId, AttendanceDTO attendanceDTO) {
        log.info("Saving attendance for employee ID: {}", employeeId);
        validateAttendanceDTO(attendanceDTO);

        EmployeeDTO employee = employeeClientService.getEmployeeById(employeeId);
        if (employee == null) {
            throw new CustomEntityNotFoundException("Employee not found by this ID: " + employeeId);
        }

        Attendance attendance = attendanceMapper.toEntity(attendanceDTO);
        attendance.setEmployeeId(employee.getId());
        attendance.setCreatedAt(LocalDateTime.now());
        attendance = attendanceRepository.save(attendance);

        log.info("Attendance saved for employee ID: {}", employeeId);
        return attendanceMapper.toDto(attendance);
    }

    @Override
    @Cacheable(value = "attendance", key = "#id")
    public AttendanceDTO getAttendanceById(Long id) {
        log.info("Fetching attendance by ID: {}", id);
        return attendanceRepository.findById(id)
                .map(attendanceMapper::toDto)
                .orElseThrow(() -> new CustomEntityNotFoundException("Attendance not found by this ID: " + id));
    }

    @Override
    @CacheEvict(value = "attendance", key = "#id")
    public AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO) {
        log.info("Updating attendance ID: {}", id);
        validateAttendanceDTO(attendanceDTO);

        return attendanceRepository.findById(id)
                .map(existingAttendance -> {
                    Attendance updatedAttendance = attendanceMapper.toEntity(attendanceDTO);
                    updatedAttendance.setId(existingAttendance.getId());
                    updatedAttendance.setCreatedAt(existingAttendance.getCreatedAt());
                    updatedAttendance.setUpdatedAt(LocalDateTime.now());
                    return attendanceMapper.toDto(attendanceRepository.save(updatedAttendance));
                })
                .orElseThrow(() -> new CustomEntityNotFoundException("Attendance not found by this ID: " + id));
    }

    @Override
    public List<AttendanceDTO> getAllAttendance(int page, int size, Sort sort) {
        log.info("Fetching all attendance records, page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size, sort);
        return attendanceRepository.findAll(pageable)
                .map(attendanceMapper::toDto)
                .getContent();
    }

    @Override
    public List<AttendanceDTO> getMonthlyAttendanceReport(Long employeeId, YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();
        log.info("Fetching monthly attendance report for employee ID: {}, month: {}", employeeId, month);
        List<Attendance> attendances = attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
        return attendances.stream().map(attendanceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<AttendanceDTO>> getAttendanceSummaryByDepartment(String departmentName, LocalDate startDate, LocalDate endDate) {
        log.info("Fetching attendance summary for department: {}, from: {} to: {}", departmentName, startDate, endDate);
        List<Attendance> attendances = attendanceRepository.findByDepartmentAndDateBetween(departmentName, startDate, endDate);
        return attendances.stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.groupingBy(AttendanceDTO::getForDepartment));
    }

    @Override
    @CacheEvict(value = "attendance", key = "#result.id")
    public AttendanceDTO markAttendanceStart(Long employeeId) {
        log.info("Marking attendance start for employee ID: {}", employeeId);
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setStartTime(LocalDateTime.now());
        attendance = attendanceRepository.save(attendance);
        return attendanceMapper.toDto(attendance);
    }

    @Override
    @CacheEvict(value = "attendance", key = "#result.id")
    public AttendanceDTO markAttendanceEnd(Long employeeId) {
        log.info("Marking attendance end for employee ID: {}", employeeId);
        Attendance attendance = attendanceRepository.findTodayAttendanceByEmployeeId(employeeId)
                .orElseThrow(() -> new CustomEntityNotFoundException("No attendance record started today for Employee ID: " + employeeId));
        attendance.setEndTime(LocalDateTime.now());
        attendance = attendanceRepository.save(attendance);
        return attendanceMapper.toDto(attendance);
    }

    @Override
    public List<AttendanceDTO> getAbsenteeismTrends(Long employeeId) {
        log.info("Fetching absenteeism trends for employee ID: {}", employeeId);
        List<Attendance> attendances = attendanceRepository.findAbsenteeismByEmployeeId(employeeId);
        return attendances.stream().map(attendanceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> generateComplianceReport(LocalDate startDate, LocalDate endDate) {
        log.info("Generating compliance report from: {} to: {}", startDate, endDate);
        List<Attendance> attendances = attendanceRepository.findByDateBetween(startDate, endDate);
        return attendances.stream().map(attendanceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Double calculateTotalWorkingHours(Long employeeId, LocalDate from, LocalDate to) {
        LocalDateTime startDateTime = from.atStartOfDay();
        LocalDateTime endDateTime = to.atTime(23, 59, 59);
        log.info("Calculating total working hours for employee ID: {}, from: {} to: {}", employeeId, from, to);

        List<Attendance> attendances = attendanceRepository.findByEmployeeIdAndStartTimeBetween(employeeId, startDateTime, endDateTime);
        return attendances.stream()
                .mapToDouble(Attendance::calculateWorkingHours)
                .sum();
    }

    private void validateAttendanceDTO(AttendanceDTO attendanceDTO) {
        if (attendanceDTO == null) {
            throw new IllegalArgumentException("AttendanceDTO must not be null");
        }
        if (!StringUtils.hasText(attendanceDTO.getAbsenteeReportedBy())) {
            throw new IllegalArgumentException("AbsenteeReportedBy must not be empty");
        }
        if (attendanceDTO.getAbsenteeDate() == null) {
            throw new IllegalArgumentException("AbsenteeDate must not be null");
        }
        // Add more validation as needed
    }
}