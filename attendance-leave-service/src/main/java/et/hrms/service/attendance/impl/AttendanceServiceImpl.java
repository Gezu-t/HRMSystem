package et.hrms.service.attendance.impl;

import et.hrms.client.employee.EmployeeClientService;
import et.hrms.dal.dto.attendance.AttendanceDTO;
import dal.dto.employee.EmployeeDTO;
import et.hrms.dal.mapper.attendance.AttendanceMapper;
import et.hrms.dal.model.attendance.Attendance;
import et.hrms.dal.repository.attendance.AttendanceRepository;
import et.hrms.exception.CustomEntityNotFoundException;
import et.hrms.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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
  public AttendanceDTO saveAttendance(Long employeeId, AttendanceDTO attendanceDTO) {
    log.info("Saving attendance for employeeprofile ID: {}", employeeId);

    validateAttendanceDTO(attendanceDTO);

    EmployeeDTO employee = employeeClientService.getEmployeeById(employeeId);
    if (employee == null) {
      throw new CustomEntityNotFoundException("Employee not found by this ID: " + employeeId);
    }

    Attendance attendance = attendanceMapper.toEntity(attendanceDTO);
    attendance.setEmployeeId(employee.getId());
    attendance.setCreatedAt(LocalDateTime.now());
    attendance = attendanceRepository.save(attendance);

    log.info("Attendance saved for employeeprofile ID: {}", employeeId);
    return attendanceMapper.toDto(attendance);
  }

  @Override
  @Cacheable(value = "attendance", key = "#id")
  public AttendanceDTO getAttendanceById(Long id) {
    log.info("Fetching attendance by ID: {}", id);
    Attendance attendance = attendanceRepository.findById(id)
            .orElseThrow(() -> new CustomEntityNotFoundException("Attendance not found by this ID: " + id));
    return attendanceMapper.toDto(attendance);
  }

  @Override
  public AttendanceDTO getAttendanceByEmployeeName(EmployeeDTO employeeDTO) {
    log.info("Fetching attendance by employee number: {}", employeeDTO.getEmployeeNumber());
    if (!StringUtils.hasText(employeeDTO.getEmployeeNumber())) {
      throw new IllegalArgumentException("Employee name must not be empty");
    }
    Attendance attendance = new Attendance();

//    Attendance attendance = attendanceRepository.findByEmployeeName(employeeDTO.getName());
//    if (attendance == null) {
//      throw new CustomEntityNotFoundException("Attendance not found for employeeprofile name: " + employeeDTO.getName());
//    }

    return attendanceMapper.toDto(attendance);
  }

  @Override
  public AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO) {
    log.info("Updating attendance ID: {}", id);

    validateAttendanceDTO(attendanceDTO);

    Attendance existingAttendance = attendanceRepository.findById(id)
            .orElseThrow(() -> new CustomEntityNotFoundException("Attendance not found by this ID: " + id));

    Attendance updatedAttendance = attendanceMapper.toEntity(attendanceDTO);
    updatedAttendance.setId(existingAttendance.getId());
    updatedAttendance.setCreatedAt(existingAttendance.getCreatedAt());
    updatedAttendance.setUpdatedAt(LocalDateTime.now());
    attendanceRepository.save(updatedAttendance);

    log.info("Attendance updated for ID: {}", id);
    return attendanceMapper.toDto(updatedAttendance);
  }

  @Override
  public List<AttendanceDTO> getAllAttendance(int page, int size, Sort sort) {
    log.info("Fetching all attendance records, page: {}, size: {}", page, size);
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Attendance> attendances = attendanceRepository.findAll(pageable);
    return attendances.stream().map(attendanceMapper::toDto).toList();
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

  @Override
  public List<AttendanceDTO> getMonthlyAttendanceReport(Long employeeId, YearMonth month) {
    LocalDate startDate = month.atDay(1);
    LocalDate endDate = month.atEndOfMonth();
    List<Attendance> attendances = attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
    return attendances.stream().map(attendanceMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Map<String, List<AttendanceDTO>> getAttendanceSummaryByDepartment(String departmentName, LocalDate startDate, LocalDate endDate) {
    List<Attendance> attendances = attendanceRepository.findByDepartmentAndDateBetween(departmentName, startDate, endDate);
    return attendances.stream()
            .map(attendanceMapper::toDto)
            .collect(Collectors.groupingBy(AttendanceDTO::getForDepartment));
  }

  @Override
  public AttendanceDTO markAttendanceStart(Long employeeId) {
    Attendance attendance = new Attendance();
    attendance.setEmployeeId(employeeId);
    attendance.setStartTime(LocalDateTime.now());
    attendance = attendanceRepository.save(attendance);
    return attendanceMapper.toDto(attendance);
  }

  @Override
  public AttendanceDTO markAttendanceEnd(Long employeeId) {
    Attendance attendance = attendanceRepository.findTodayAttendanceByEmployeeId(employeeId)
            .orElseThrow(() -> new CustomEntityNotFoundException("No attendance record started today for Employee ID: " + employeeId));
    attendance.setEndTime(LocalDateTime.now());
    attendance = attendanceRepository.save(attendance);
    return attendanceMapper.toDto(attendance);
  }

  @Override
  public List<AttendanceDTO> getAbsenteeismTrends(Long employeeId) {
    List<Attendance> attendances = attendanceRepository.findAbsenteeismByEmployeeId(employeeId);
    return attendances.stream().map(attendanceMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<AttendanceDTO> generateComplianceReport(LocalDate startDate, LocalDate endDate) {
    List<Attendance> attendances = attendanceRepository.findByDateBetween(startDate, endDate);
    return attendances.stream().map(attendanceMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Double calculateTotalWorkingHours(Long employeeId, LocalDate from, LocalDate to) {
    LocalDateTime startDateTime = from.atStartOfDay();
    LocalDateTime endDateTime = to.atTime(23, 59, 59);

    List<Attendance> attendances = attendanceRepository.findByEmployeeIdAndStartTimeBetween(employeeId, startDateTime, endDateTime);
    return attendances.stream()
            .mapToDouble(Attendance::calculateWorkingHours)
            .sum();
  }
}
