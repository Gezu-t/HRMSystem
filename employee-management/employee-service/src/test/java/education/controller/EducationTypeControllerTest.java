package education.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.employee.core.controller.education.impl.EducationTypeControllerImpl;
import com.hrms.employee.core.dal.dto.education.EducationTypeDTO;
import com.hrms.employee.core.service.education.EducationTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EducationTypeControllerTest {

  private MockMvc mockMvc;

  @Mock
  private EducationTypeService educationTypeService;

  @InjectMocks
  private EducationTypeControllerImpl educationTypeController;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(educationTypeController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void testGetAllEducationTypes() throws Exception {
    EducationTypeDTO educationTypeDTO = new EducationTypeDTO();
    educationTypeDTO.setEducationTypeId(1L);
    educationTypeDTO.setName("Bachelor");

    List<EducationTypeDTO> educationTypeDTOList = new ArrayList<>();
    educationTypeDTOList.add(educationTypeDTO);

    when(educationTypeService.getAllEducationTypes()).thenReturn(educationTypeDTOList);

    mockMvc.perform(get("/api/educationTypes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].educationTypeId", is(educationTypeDTO.getEducationTypeId().intValue())))
            .andExpect(jsonPath("$[0].name", is(educationTypeDTO.getName())));
  }

  @Test
  void testCreateEducationType() throws Exception {
    EducationTypeDTO educationTypeDTO = new EducationTypeDTO();
    educationTypeDTO.setName("Bachelor");

    mockMvc.perform(post("/api/educationTypes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(educationTypeDTO)))
            .andExpect(status().isCreated());
  }

  @Test
  void testUpdateEducationType() throws Exception {
    EducationTypeDTO educationTypeDTO = new EducationTypeDTO();
    educationTypeDTO.setEducationTypeId(1L);
    educationTypeDTO.setName("Master");

    mockMvc.perform(put("/api/educationTypes/" + educationTypeDTO.getEducationTypeId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(educationTypeDTO)))
            .andExpect(status().isOk());
  }
}