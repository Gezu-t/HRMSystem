package education;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmsystem.employeeprofileservice.controller.education.EducationLevelControllerImpl;
import com.hrmsystem.employeeprofileservice.dal.dto.education.EducationLevelDTO;
import com.hrmsystem.employeeprofileservice.service.education.EducationLevelService;
import exceptions.GlobalExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EducationLevelControllerTest {
  private MockMvc mockMvc;

  @Mock
  private EducationLevelService educationLevelService;

  @InjectMocks
  private EducationLevelControllerImpl educationLevelController;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(educationLevelController)
            .setControllerAdvice(new GlobalExceptionHandler())  // Add global exception handler
            .build();
  }

  @Test
  public void testGetEducationLevelById() throws Exception {
    Long id = 1L;
    EducationLevelDTO educationLevelDTO = new EducationLevelDTO();
    educationLevelDTO.setEducationLevelId(id);
    educationLevelDTO.setEducationLevelName("Bachelor");

    when(educationLevelService.findEducationLevelById(id)).thenReturn(educationLevelDTO);

    mockMvc.perform(get("/api/educationLevels/" + id))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(educationLevelDTO)));
  }

  @Test
  public void testCreateEducationLevel() throws Exception {
    EducationLevelDTO educationLevelDTO = new EducationLevelDTO();
    // ID should not be set for creation
    educationLevelDTO.setEducationLevelName("Bachelor");

    mockMvc.perform(post("/api/educationLevels")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(educationLevelDTO)))
            .andExpect(status().isCreated());
  }

  @Test
  public void testUpdateEducationLevel() throws Exception {
    Long id = 1L;
    EducationLevelDTO educationLevelDTO = new EducationLevelDTO();
    educationLevelDTO.setEducationLevelId(id);
    educationLevelDTO.setEducationLevelName("Master");

    when(educationLevelService.updateEducationLevel(educationLevelDTO)).thenReturn(educationLevelDTO);

    mockMvc.perform(put("/api/educationLevels/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(educationLevelDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(educationLevelDTO)));
  }
}
