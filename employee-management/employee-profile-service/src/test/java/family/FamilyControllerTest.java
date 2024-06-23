package family;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmsystem.employeeprofileservice.controller.employee.imp.FamilyControllerImpl;
import com.hrmsystem.employeeprofileservice.dal.dto.employee.FamilyDTO;
import com.hrmsystem.employeeprofileservice.service.employee.FamilyService;
import com.hrmsystem.employeeservice.core.dal.model.employee.Family;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FamilyControllerTest {
  private MockMvc mockMvc;
  @InjectMocks
  private FamilyControllerImpl familyController;
  @Mock
  private FamilyService familyService;
  private FamilyDTO familyDTO;
  private Family family;

  @Before
  public void setUP() {

    mockMvc = MockMvcBuilders.standaloneSetup(familyController).build();

    familyDTO = new FamilyDTO();
    familyDTO.setId(1L);
    familyDTO.setNationality("Italy");
    familyDTO.setGender("M");
    familyDTO.setFamilyFirstName("Test one");
    familyDTO.setFamilyLastName("Test Two");
    familyDTO.setEmployeeId(1L);
    family = new Family();
    family.setId(1L);
  }

  private static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testCreateFamily() throws Exception {
    Long employeeId = 1L;

    when(familyService.createEmployeeFamily(eq(employeeId), any(FamilyDTO.class))).thenReturn(familyDTO);

    mockMvc.perform(post("/api/families/{employeeId}", employeeId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(familyDTO)))
            .andExpect(status().isCreated());

  }

  @Test
  public void testGetFamilyById() throws Exception {
    Long familyId = 1L;

//    when(familyService.getFamilyById(eq(familyId))).thenReturn(familyDTO);

    mockMvc.perform(post("/api/families/{familyId}", familyId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(familyDTO)))
            .andExpect(status().isCreated());

  }

  @Test
  public void testGetAllFamilies() throws Exception {
    List<FamilyDTO> familyDTOS = new ArrayList<>();
    familyDTOS.add(familyDTO);

    given(familyService.getAllFamily(0, 10, Sort.by("id"))).willReturn(familyDTOS);

    mockMvc.perform(get("/api/families")
                    .param("page", "0")
                    .param("size", "10")
                    .param("sort", "id, Asc"))
            .andExpect(status().isOk());

  }


}
