package education;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import et.hrms.controller.impl.EducationControllerImpl;
import et.hrms.dal.dto.EducationDTO;
import et.hrms.service.EducationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EducationControllerTest {

    @Mock
    private EducationService educationService;
    @InjectMocks
    private EducationControllerImpl educationController;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    private EducationDTO educationDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(educationController).build();
        objectMapper.registerModule(new JavaTimeModule()); // Add this line

        String completionDate = "01-01-2022";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate completionDate1 = LocalDate.parse(completionDate, formatter);

        educationDTO = new EducationDTO();
        educationDTO.setEducationCompletionDate(completionDate1);
        educationDTO.setEducationLevel("Bachelor");
        educationDTO.setInstitution("Test University");
        educationDTO.setDegree("Bachelor of Science");
        educationDTO.setEducationMajor("Computer Science");
        educationDTO.setEducationMinor("Mathematics");
        educationDTO.setEducationGrade("A");
        educationDTO.setEducationType("Full-time");
        educationDTO.setEducationStatus("Ongoing");
        educationDTO.setAward("Dean's List");
        educationDTO.setAwardDate(LocalDate.now().plusYears(2));
        educationDTO.setEducationStartDate(LocalDate.now());
        educationDTO.setEducationEndDate(LocalDate.now().plusYears(4));
        educationDTO.setAwardDescription("Top 10% of students in the faculty");

    }

    // Test case for creating a new Education record
    @Test
    void testCreateEducation() throws Exception {
        doNothing().when(educationService).createEducation(any(EducationDTO.class));
        MvcResult result = mockMvc.perform(post("/api/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(educationDTO)))
                .andExpect(status().isCreated()) // Or status().isOk() if appropriate
                .andReturn();

        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());

        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Response content: " + responseContent);

        verify(educationService, times(1)).createEducation(any(EducationDTO.class));
    }

    // Test case for updating an existing Education record
    @Test
    void testUpdateEducationInfo() throws Exception {
        educationDTO.setEducationId(1L);
        when(educationService.updateEducationInfo(any(EducationDTO.class))).thenReturn(educationDTO);
        mockMvc.perform(put("/api/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(educationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.educationId").value(educationDTO.getEducationId()))
                .andExpect(jsonPath("$.institution").value(educationDTO.getInstitution()))
                .andExpect(jsonPath("$.degree").value(educationDTO.getDegree()));
    }

    // Test case for fetching an Education record by institution name
    @Test
    void testGetEducationByInstitution() throws Exception {
        when(educationService.getEducationByInstitution(educationDTO.getInstitution())).thenReturn(educationDTO);

        mockMvc.perform(get("/api/education/institution/{name}", educationDTO.getInstitution())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.educationId").value(educationDTO.getEducationId()))
                .andExpect(jsonPath("$.institution").value(educationDTO.getInstitution()))
                .andExpect(jsonPath("$.degree").value(educationDTO.getDegree()));
    }

    // Test case for fetching all Education records with pagination
    @Test
    void testGetAllEducationList() throws Exception {
        List<EducationDTO> educationDTOs = Collections.singletonList(educationDTO);
        when(educationService.getAllEducationList(0, 5, null)).thenReturn(educationDTOs);

        mockMvc.perform(get("/api/education?page=0&size=5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].educationId").value(educationDTO.getEducationId()))
                .andExpect(jsonPath("$[0].institution").value(educationDTO.getInstitution()))
                .andExpect(jsonPath("$[0].degree").value(educationDTO.getDegree()));
    }

    // Test case for creating a new Education record with invalid input
    @Test
    void testCreateEducationInvalidInput() throws Exception {
        mockMvc.perform(post("/api/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    // Test case for updating an existing Education record with invalid input
    @Test
    void testUpdateEducationInfoInvalidInput() throws Exception {
        EducationDTO invalidEducationDTO = new EducationDTO();
        invalidEducationDTO.setEducationId(1L);
        invalidEducationDTO.setInstitution(null);
        invalidEducationDTO.setDegree(null);

        mockMvc.perform(put("/api/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEducationDTO)))
                .andExpect(status().isBadRequest());
    }

    // Test case for fetching an Education record by institution name when it's not found
    @Test
    void testGetEducationByInstitutionNotFound() throws Exception {
        String nonExistentInstitution = "Non-existent Institution";
        when(educationService.getEducationByInstitution(nonExistentInstitution)).thenReturn(null);

        mockMvc.perform(get("/api/education/institution/{name}", nonExistentInstitution)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}

