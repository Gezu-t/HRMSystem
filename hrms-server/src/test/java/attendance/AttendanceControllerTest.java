package attendance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import et.hrms.controller.project.AttendanceControllerImpl;
import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.service.AttendanceService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class AttendanceControllerTest {

  private MockMvc mockMvc;
  @Mock
  private AttendanceService attendanceService;

  @InjectMocks
  private AttendanceControllerImpl attendanceController;

  private AttendanceDTO attendanceDTO;
  private ObjectMapper objectMapper = new ObjectMapper()
          .registerModule(new JavaTimeModule());

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(attendanceController).build();
    attendanceDTO = new AttendanceDTO();
    attendanceDTO.setAttendanceId(1L);
    attendanceDTO.setEmployeeId(1L);
  }

  public static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final var jsonContent = mapper.writeValueAsString(obj);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testCreateAttendance() throws Exception {
    var employeeId = 1L;
    when(attendanceService.saveAttendance(anyLong(), any(AttendanceDTO.class))).thenReturn(attendanceDTO);
    objectMapper.registerModule(new JavaTimeModule());
    mockMvc.perform(post("/api/attendances/{employeeId}", employeeId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(attendanceDTO)))
            .andExpect(status().isCreated());
  }

  @Test
  public void testGetAttendanceById() throws Exception {
    var attendanceId = 1L;
    when(attendanceService.getAttendanceById(anyLong())).thenReturn(attendanceDTO);

    mockMvc.perform(get("/api/attendances/{attendanceId}", attendanceId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentType(asJsonString(attendanceDTO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testGetAllAttendance() throws Exception {

    List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
    attendanceDTOS.add(attendanceDTO);

    given(attendanceService.getAllAttendance(0, 10, Sort.by("id"))).willReturn(attendanceDTOS);

    mockMvc.perform(get("/api/attendances")
                    .param("page", "0")
                    .param("size", "10")
                    .param("sort", "id, Asc"))
            .andExpect(status().isOk());

  }


}
