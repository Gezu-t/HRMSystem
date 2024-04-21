package education;

import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.controller.education.EducationControllerImpl;
import et.hrms.dal.dto.education.EducationDTO;
import et.hrms.service.education.EducationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EducationControllerTest {

    @Mock
    private EducationService educationService;

    @InjectMocks
    private EducationControllerImpl educationController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(educationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateEducation() throws Exception {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setInstitution("Test Institution");
        String jsonRequest = objectMapper.writeValueAsString(educationDTO);

        mockMvc.perform(post("/api/education/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    // ...

    @Test
    void testUpdateEducationInfo() throws Exception {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setInstitution("Test Institution");
        educationDTO.setEducationId(1L);
        String jsonRequest = objectMapper.writeValueAsString(educationDTO);

        when(educationService.updateEducationInfo(anyLong(), any(EducationDTO.class))).thenReturn(educationDTO);

        mockMvc.perform(put("/api/education/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.institution").value("Test Institution"))
                .andExpect(jsonPath("$.educationId").value(1L));
    }

    @Test
    void testGetEducationByInstitution() throws Exception {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setInstitution("Test Institution");
        educationDTO.setEducationId(1L);

        when(educationService.getEducationByInstitution("Test Institution")).thenReturn(educationDTO);

        mockMvc.perform(get("/api/education/institution/Test Institution"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.institution").value("Test Institution"))
                .andExpect(jsonPath("$.educationId").value(1L));
    }

    @Test
    void testGetAllEducationList() throws Exception {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setInstitution("Test Institution");
        educationDTO.setEducationId(1L);
        List<EducationDTO> educationList = Collections.singletonList(educationDTO);

        when(educationService.getAllEducationList(anyInt(), anyInt(), any(Sort.class))).thenReturn(educationList);

        mockMvc.perform(get("/api/education?page=0&size=10&direction=ASC&sortBy=institution"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].institution").value("Test Institution"))
                .andExpect(jsonPath("$.[0].educationId").value(1L));
    }



}
