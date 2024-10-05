package attendance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import et.hrms.controller.attendance.impl.AttendanceControllerImpl;
import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.service.attendance.AttendanceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AttendanceControllerTest {

  private MockMvc mockMvc;

  @Mock
  private AttendanceService attendanceService;

  @InjectMocks
  private AttendanceControllerImpl attendanceController;

  private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(attendanceController).build();
  }

  @Test
  public void testCreateAttendance() throws Exception {
    AttendanceDTO attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);

    when(attendanceService.saveAttendance(eq(1L), any(AttendanceDTO.class))).thenReturn(attendanceDTO);
    mockMvc.perform(post("/api/attendances/{employeeId}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(attendanceDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(attendanceDTO)));
  }

  @Test
  public void testGetAttendanceById() throws Exception {
    AttendanceDTO attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);

    when(attendanceService.getAttendanceById(1L)).thenReturn(attendanceDTO);
    mockMvc.perform(get("/api/attendances/{attendanceId}", 1L)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(attendanceDTO)));
  }

  @Test
  public void testUpdateAttendance() throws Exception {
    AttendanceDTO attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);

    when(attendanceService.updateAttendance(eq(1L), any(AttendanceDTO.class))).thenReturn(attendanceDTO);
    mockMvc.perform(put("/api/attendances/{attendanceId}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(attendanceDTO)))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(attendanceDTO)));
  }

  @Test
  public void testGetAllAttendance() throws Exception {
    List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
    AttendanceDTO attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);
    attendanceDTOS.add(attendanceDTO);

    // Ensure mock returns the expected data
    when(attendanceService.getAllAttendance(anyInt(), anyInt(), any(Sort.class))).thenReturn(attendanceDTOS);

    // Perform the request and verify the response
    mockMvc.perform(get("/api/attendances")
                    .param("page", "0")
                    .param("size", "10")
                    .param("sort", "id,asc"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(attendanceDTOS)));
  }

  @Test
  public void testGetMonthlyAttendanceReport() throws Exception {
    List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
    AttendanceDTO attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);
    attendanceDTOS.add(attendanceDTO);
    YearMonth month = YearMonth.now();

    when(attendanceService.getMonthlyAttendanceReport(1L, month)).thenReturn(attendanceDTOS);
    mockMvc.perform(get("/api/attendances/monthly-report/{employeeId}", 1L)
                    .param("month", month.toString()))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(attendanceDTOS)));
  }

  @Test
  public void testGetAttendanceSummaryByDepartment() throws Exception {
    Map<String, List<AttendanceDTO>> summary = new HashMap<>();
    List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
    AttendanceDTO attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);
    attendanceDTOS.add(attendanceDTO);
    summary.put("Engineering", attendanceDTOS);
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(1);

    when(attendanceService.getAttendanceSummaryByDepartment(eq("Engineering"), eq(startDate), eq(endDate))).thenReturn(summary);
    mockMvc.perform(get("/api/attendances/department-summary")
                    .param("department", "Engineering")
                    .param("startDate", startDate.toString())
                    .param("endDate", endDate.toString()))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(summary)));
  }

  @Test
  public void testMarkAttendanceStart() throws Exception {
    AttendanceDTO attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);

    when(attendanceService.markAttendanceStart(1L)).thenReturn(attendanceDTO);
    mockMvc.perform(post("/api/attendances/start/{employeeId}", 1L)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(attendanceDTO)));
  }

  @Test
  public void testMarkAttendanceEnd() throws Exception {
    AttendanceDTO attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);

    when(attendanceService.markAttendanceEnd(1L)).thenReturn(attendanceDTO);
    mockMvc.perform(post("/api/attendances/end/{employeeId}", 1L)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(attendanceDTO)));
  }

  @Test
  public void testCalculateTotalWorkingHours() throws Exception {
    when(attendanceService.calculateTotalWorkingHours(1L, LocalDate.now(), LocalDate.now().plusDays(1)))
            .thenReturn(8.0);

    mockMvc.perform(get("/api/attendances/working-hours")
                    .param("employeeId", "1")
                    .param("from", LocalDate.now().toString())
                    .param("to", LocalDate.now().plusDays(1).toString()))
            .andExpect(status().isOk())
            .andExpect(content().string("8.0"));
  }

  @Test
  public void testGenerateComplianceReport() throws Exception {
    List<AttendanceDTO> report = new ArrayList<>();
    AttendanceDTO attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);
    report.add(attendanceDTO);
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(1);

    when(attendanceService.generateComplianceReport(eq(startDate), eq(endDate))).thenReturn(report);
    mockMvc.perform(get("/api/attendances/compliance-report")
                    .param("startDate", startDate.toString())
                    .param("endDate", endDate.toString()))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(report)));
  }
}
