package education;


import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.controller.education.EducationTypeControllerImpl;
import et.hrms.dal.dto.education.EducationTypeDTO;
import et.hrms.service.education.EducationTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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

@RunWith(MockitoJUnitRunner.class)
public class EducationTypeControllerTest {

  private MockMvc mockMvc;

  @Mock
  private EducationTypeService educationTypeService;

  @InjectMocks
  private EducationTypeControllerImpl educationTypeController;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(educationTypeController).build();
  }

  @Test
  public void testGetAllEducationTypes() throws Exception {
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
  public void testCreateEducationType() throws Exception {
    EducationTypeDTO educationTypeDTO = new EducationTypeDTO();
    educationTypeDTO.setName("Bachelor");

    mockMvc.perform(post("/api/educationTypes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(educationTypeDTO)))
            .andExpect(status().isCreated());

  }

  @Test
  public void testUpdateEducationType() throws Exception {
    EducationTypeDTO educationTypeDTO = new EducationTypeDTO();
    educationTypeDTO.setEducationTypeId(1L);
    educationTypeDTO.setName("Master");


    mockMvc.perform(put("/api/educationTypes/"+ educationTypeDTO.getEducationTypeId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(educationTypeDTO)))
            .andExpect(status().isOk());
//            .andExpect(jsonPath("$.educationTypeId", is(educationTypeDTO.getEducationTypeId().intValue())))
//            .andExpect(jsonPath("$.name", is(educationTypeDTO.getName())));
  }



  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
