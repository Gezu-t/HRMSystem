package et.hrms.service.project;

import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.dal.mapping.AttendanceMapper;
import et.hrms.dal.model.attendance.Attendance;
import et.hrms.dal.model.employee.Employee;
import et.hrms.dal.repository.attendance.AttendanceRepository;
import et.hrms.dal.repository.employee.EmployeeRepository;
import et.hrms.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

  private final AttendanceRepository attendanceRepository;

  private final AttendanceMapper attendanceMapper;
  private final EmployeeRepository employeeRepository;


  @Override
  public AttendanceDTO saveAttendance(Long employeeId, AttendanceDTO attendanceDTO) {
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee information is not found by this Id: {0}" + employeeId));
    Attendance attendance = attendanceMapper.toEntity(attendanceDTO);
    attendance.setEmployee(employee);
    attendance = attendanceRepository.save(attendance);
    return attendanceMapper.toDto(attendance);
  }

  @Override
  public AttendanceDTO getAttendanceById(Long id) {
    Attendance attendance = attendanceRepository.findById(id).orElse(null);
    if (attendance == null) {
      return null;
    }
    return attendanceMapper.toDto(attendance);
  }

  @Override
  public AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO) {
    Attendance existingAttendance = attendanceRepository.findById(id).orElse(null);
    if (existingAttendance == null) {
      return null;
    }
    Attendance updatedAttendance = attendanceMapper.toEntity(attendanceDTO);
    updatedAttendance.setId(existingAttendance.getId());
    updatedAttendance.setCreatedAt(existingAttendance.getCreatedAt());
    updatedAttendance.setUpdateAt(LocalDateTime.now());
    updatedAttendance = attendanceRepository.save(updatedAttendance);
    return attendanceMapper.toDto(updatedAttendance);
  }

  @Override
  public List<AttendanceDTO> getAllAttendance(int page, int size, Sort sort) {
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Attendance> attendances = attendanceRepository.findAll(pageable);
    return attendances.stream().map(attendanceMapper::toDto).toList();
  }


}
