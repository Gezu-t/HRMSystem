package education;

import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.controller.education.EducationLevelControllerImpl;
import et.hrms.dal.dto.education.EducationLevelDTO;
import et.hrms.service.education.EducationLevelService;
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
    mockMvc = MockMvcBuilders.standaloneSetup(educationLevelController).build();
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
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

  }

  @Test
  public void testCreateEducationLevel() throws Exception {
    EducationLevelDTO educationLevelDTO = new EducationLevelDTO();
    educationLevelDTO.setEducationLevelName("Bachelor");

    mockMvc.perform(post("/api/educationLevels")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(educationLevelDTO)))
            .andExpect(status().isCreated());

  }

  @Test
  public void testUpdateEducationLevel() throws Exception {
    Long id = 1L;
    EducationLevelDTO educationLevelDTO = new EducationLevelDTO();
    educationLevelDTO.setEducationLevelId(id);
    educationLevelDTO.setEducationLevelName("Master");
    String eduLevel = objectMapper.writeValueAsString(educationLevelDTO);

    mockMvc.perform(put("/api/educationLevels/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(eduLevel))
                    .andExpect(status().isOk());
  }

}

