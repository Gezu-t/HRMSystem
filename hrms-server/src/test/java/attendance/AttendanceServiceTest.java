package attendance;


import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.dal.mapping.AttendanceMapper;
import et.hrms.dal.model.attendance.Attendance;
import et.hrms.dal.model.employee.Employee;
import et.hrms.dal.repository.attendance.AttendanceRepository;
import et.hrms.dal.repository.employee.EmployeeRepository;
import et.hrms.service.project.AttendanceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AttendanceServiceTest {

  @Mock
  private AttendanceRepository attendanceRepository;
  @Mock
  private EmployeeRepository employeeRepository;
  @Mock
  private AttendanceMapper attendanceMapper;
  @InjectMocks
  private AttendanceServiceImpl attendanceService;
  private AttendanceDTO attendanceDTO;
  private Attendance attendance;
  private Employee employee;

  @Before
  public void setUp() {
    attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendance = new Attendance();
    attendance.setId(1L);

    employee = new Employee();
    employee.setId(1L);
    attendanceDTO.setEmployeeId(employee.getId());
  }

  @Test
  public void testSaveAttendance() {

    when(attendanceMapper.toEntity(attendanceDTO)).thenReturn(attendance);
    when(attendanceRepository.save(attendance)).thenReturn(attendance);
    when(attendanceMapper.toDto(attendance)).thenReturn(attendanceDTO);
    when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(employee));
    AttendanceDTO savedAttendanceDTO = attendanceService.saveAttendance(anyLong(), attendanceDTO);
    assertEquals(attendanceDTO, savedAttendanceDTO);
    verify(attendanceMapper, times(1)).toEntity(attendanceDTO);
    verify(attendanceRepository, times(1)).save(attendance);
    verify(attendanceMapper, times(1)).toDto(attendance);
  }

  @Test
  public void testGetAttendanceById() {
    Long id = attendance.getId();
    when(attendanceRepository.findById(id)).thenReturn(Optional.of(attendance));
    when(attendanceMapper.toDto(attendance)).thenReturn(attendanceDTO);
    AttendanceDTO foundAttendanceDTO = attendanceService.getAttendanceById(id);
    assertEquals(attendanceDTO, foundAttendanceDTO);
    verify(attendanceRepository, times(1)).findById(id);
    verify(attendanceMapper, times(1)).toDto(attendance);
  }

  @Test
  public void testUpdateAttendance() {
    Long id = 1L;
    attendanceDTO.setAttendanceId(id);
    Attendance existingAttendance = new Attendance();
    existingAttendance.setId(id);
    Attendance updatedAttendance = new Attendance();
    updatedAttendance.setId(id);
    when(attendanceRepository.findById(id)).thenReturn(Optional.of(existingAttendance));
    when(attendanceMapper.toEntity(attendanceDTO)).thenReturn(updatedAttendance);
    when(attendanceRepository.save(updatedAttendance)).thenReturn(updatedAttendance);
    when(attendanceMapper.toDto(updatedAttendance)).thenReturn(attendanceDTO);
    AttendanceDTO updatedAttendanceDTO = attendanceService.updateAttendance(id, attendanceDTO);
    assertEquals(attendanceDTO, updatedAttendanceDTO);
    verify(attendanceRepository, times(1)).findById(id);
    verify(attendanceMapper, times(1)).toEntity(attendanceDTO);
    verify(attendanceRepository, times(1)).save(updatedAttendance);
    verify(attendanceMapper, times(1)).toDto(updatedAttendance);
  }

  @Test
  public void testGetAllAttendance() {
    int page = 0;
    int size = 2;
    Sort sortOrder = Sort.by(Sort.Direction.ASC, "id");
    List<Attendance> attendanceList = new ArrayList<>();
    attendanceList.add(new Attendance());
    attendanceList.add(new Attendance());
    Page<Attendance> attendancePage = new PageImpl<>(attendanceList, PageRequest.of(page, size, sortOrder), attendanceList.size());
    AtomicReference<List<AttendanceDTO>> attendanceDTOList = new AtomicReference<>(new ArrayList<>());
    attendanceDTOList.get().add(new AttendanceDTO());
    attendanceDTOList.get().add(new AttendanceDTO());
    Mockito.when(attendanceRepository.findAll(Mockito.any(Pageable.class))).thenReturn(attendancePage);
    Mockito.when(attendanceMapper.toDto(Mockito.any(Attendance.class))).thenReturn(new AttendanceDTO());
    List<AttendanceDTO> foundAttendanceDTOPage = attendanceService.getAllAttendance(page, size, sortOrder);
    assertEquals(2, foundAttendanceDTOPage.size());
    Mockito.verify(attendanceRepository, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
    Mockito.verify(attendanceMapper, Mockito.times(2)).toDto(Mockito.any(Attendance.class));
  }


}
