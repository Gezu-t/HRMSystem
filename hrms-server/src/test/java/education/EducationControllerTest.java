package education;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import et.hrms.controller.EducationController;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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

    private EducationDTO educationDTO;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(educationController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Add this line

        educationDTO = new EducationDTO();
        educationDTO.setEducationId(1L);
        educationDTO.setInstitution("Institution");
        educationDTO.setDegree("Degree");
        String startDate = "01-09-2021";
        String endDate = "12-05-2023";
        String completionDate = "01-01-2022";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate educationStartDate = LocalDate.parse(startDate, formatter);
        LocalDate educationEndDate = LocalDate.parse(endDate, formatter);
        LocalDate completionDate1 = LocalDate.parse(completionDate, formatter);

        educationDTO.setEducationStartDate(educationStartDate);
        educationDTO.setEducationEndDate(educationEndDate);


    }


    @Test
    public void testCreateEducation() throws Exception {
        // In the test method or @BeforeEach setup method, depending on your use case
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setInstitution("Institution");
        educationDTO.setDegree("Degree");
// Set other fields as needed

        doNothing().when(educationService).createEducation(educationDTO);

        MvcResult result = mockMvc.perform(post("/api/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(educationDTO)))
                .andExpect(status().is(any(Integer.class)))
                .andReturn();

        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());


        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Response content: " + responseContent);

        verify(educationService, times(1)).createEducation(any(EducationDTO.class));
    }


    @Test
    public void testUpdateEducationInfo() throws Exception {
        // In the test method or @BeforeEach setup method, depending on your use case
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setInstitution("Institution");
        educationDTO.setDegree("Degree");
        // Set other fields as needed

        when(educationService.updateEducationInfo(any(EducationDTO.class))).thenReturn(educationDTO);

        mockMvc.perform(put("/api/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(educationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.educationId").value(educationDTO.getEducationId()))
                .andExpect(jsonPath("$.institution").value(educationDTO.getInstitution()))
                .andExpect(jsonPath("$.degree").value(educationDTO.getDegree()));
    }

    @Test
    public void testGetEducationByInstitution() throws Exception {
        when(educationService.getEducationByInstitution(educationDTO.getInstitution())).thenReturn(educationDTO);

        mockMvc.perform(get("/api/education/institution/{name}", educationDTO.getInstitution())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.educationId").value(educationDTO.getEducationId()))
                .andExpect(jsonPath("$.institution").value(educationDTO.getInstitution()))
                .andExpect(jsonPath("$.degree").value(educationDTO.getDegree()));
    }

    @Test
    public void testGetAllEducationList() throws Exception {
        List<EducationDTO> educationDTOs = Arrays.asList(educationDTO);
        when(educationService.getAllEducationList(0, 5, null)).thenReturn(educationDTOs);

        mockMvc.perform(get("/api/education?page=0&size=5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].educationId").value(educationDTO.getEducationId()))
                .andExpect(jsonPath("$[0].institution").value(educationDTO.getInstitution()))
                .andExpect(jsonPath("$[0].degree").value(educationDTO.getDegree()));
    }
    @Test
    public void testCreateEducationInvalidInput() throws Exception {
        // In the test method or @BeforeEach setup method, depending on your use case
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setInstitution("Institution");
        educationDTO.setDegree("Degree");
        // Set other fields as needed


        mockMvc.perform(post("/api/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateEducationInfoInvalidInput() throws Exception {
        EducationDTO invalidEducationDTO = new EducationDTO();
        invalidEducationDTO.setEducationId(1L);
        invalidEducationDTO.setInstitution(null);
        invalidEducationDTO.setDegree(null);

        mockMvc.perform(put("/api/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEducationDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetEducationByInstitutionNotFound() throws Exception {
        String nonExistentInstitution = "Non-existent Institution";
        when(educationService.getEducationByInstitution(nonExistentInstitution)).thenReturn(null);

        mockMvc.perform(get("/api/education/institution/{name}", nonExistentInstitution)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}

