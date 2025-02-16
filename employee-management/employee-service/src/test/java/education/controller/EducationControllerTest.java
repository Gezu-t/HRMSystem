package education.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.employee.core.controller.education.impl.EducationControllerImpl;
import com.hrms.employee.core.dal.dto.education.EducationDTO;
import com.hrms.employee.core.service.education.EducationService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
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
        // Given
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setInstitution("Test Institution");
        String jsonRequest = objectMapper.writeValueAsString(educationDTO);

        // Mocking the void method
        doNothing().when(educationService).createEducation(anyLong(), any(EducationDTO.class));

        // When & Then
        mockMvc.perform(post("/api/education/employee/1") // Corrected URL
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }


    @Test
    void testUpdateEducationInfo() throws Exception {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setInstitution("Test Institution");
        educationDTO.setEducationId(1L);
        String jsonRequest = objectMapper.writeValueAsString(educationDTO);

        // Mock the service behavior
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

        // Mock the service behavior
        when(educationService.getEducationByInstitution(anyString())).thenReturn(educationDTO);

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

        // Mock the service behavior
        when(educationService.getAllEducationList(anyInt(), anyInt(), any(Sort.class))).thenReturn(educationList);

        mockMvc.perform(get("/api/education?page=0&size=10&direction=ASC&sortBy=institution"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].institution").value("Test Institution"))
                .andExpect(jsonPath("$.[0].educationId").value(1L));
    }
}
