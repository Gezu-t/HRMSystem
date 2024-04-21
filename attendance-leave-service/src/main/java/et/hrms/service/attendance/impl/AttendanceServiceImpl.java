package et.hrms.service.attendance.impl;

import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.dal.mapper.attendance.AttendanceMapper;
import et.hrms.dal.model.attendance.Attendance;
import et.hrms.dal.repository.attendance.AttendanceRepository;
import et.hrms.service.attendance.AttendanceService;
import et.hrms.service.employee.EmployeeClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

  private final AttendanceRepository attendanceRepository;
  private final AttendanceMapper attendanceMapper;
  private final EmployeeClientService employeeClientService;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper, EmployeeClientService employeeClientService) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
        this.employeeClientService = employeeClientService;
    }

    @Override
  public AttendanceDTO saveAttendance(Long employeeId, AttendanceDTO attendanceDTO) {
    EmployeeDTO employee = employeeClientService.getEmployeeById(employeeId);
    Attendance attendance = attendanceMapper.toEntity(attendanceDTO);
    attendance.setEmployeeId(employee.getId());
    attendance = attendanceRepository.save(attendance);
    return attendanceMapper.toDto(attendance);
  }

  @Override
  public AttendanceDTO getAttendanceById(Long id) {
    Attendance attendance = attendanceRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Attendance not found by this ID: " + id));
    return attendanceMapper.toDto(attendance);
  }

  @Override
  public AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO) {
    Attendance existingAttendance = attendanceRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Attendance not found by this ID: " + id));
    Attendance updatedAttendance = attendanceMapper.toEntity(attendanceDTO);
    updatedAttendance.setId(existingAttendance.getId());
    updatedAttendance.setCreatedAt(existingAttendance.getCreatedAt());
    updatedAttendance.setUpdatedAt(LocalDateTime.now());
    attendanceRepository.save(updatedAttendance);
    return attendanceMapper.toDto(updatedAttendance);
  }

  @Override
  public List<AttendanceDTO> getAllAttendance(int page, int size, Sort sort) {
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Attendance> attendances = attendanceRepository.findAll(pageable);
    return attendances.stream().map(attendanceMapper::toDto).toList();
  }
}
