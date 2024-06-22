package organization;

import et.hrms.controller.structure.impl.OrganizationControllerImpl;
import et.hrms.dal.dto.department.OrganizationDTO;
import et.hrms.service.department.OrganizationService;
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

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrganizationControllerTest {

    @InjectMocks
    private OrganizationControllerImpl organizationController;

    @Mock
    private OrganizationService organizationService;

    private MockMvc mockMvc;
    private OrganizationDTO organizationDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(organizationController).build();

        organizationDTO = new OrganizationDTO();
        organizationDTO.setId(1L);
        organizationDTO.setOrganizationName("Organization");
        organizationDTO.setEstablishmentDate(LocalDate.now());
        organizationDTO.setOwnerName("Owner");
    }

    @Test
    void createOrganization() throws Exception {
        doNothing().when(organizationService).createOrganization(any(OrganizationDTO.class));

        mockMvc.perform(post("/api/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"organizationName\": \"Organization\", \"establishmentDate\": \"2023-06-15\", \"ownerName\": \"Owner\"}"))
                .andExpect(status().isCreated());

        verify(organizationService, times(1)).createOrganization(any(OrganizationDTO.class));
    }

    @Test
    void getOrganizationById() throws Exception {
        when(organizationService.getOrganizationById(anyLong())).thenReturn(organizationDTO);

        mockMvc.perform(get("/api/organizations/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.organizationName").value("Organization"));

        verify(organizationService, times(1)).getOrganizationById(anyLong());
    }

    @Test
    void updateOrganization() throws Exception {
        when(organizationService.updateOrganization(anyLong(), any(OrganizationDTO.class))).thenReturn(organizationDTO);

        mockMvc.perform(put("/api/organizations/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"organizationName\": \"Updated Organization\", \"establishmentDate\": \"2023-06-15\", \"ownerName\": \"Owner\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.organizationName").value("Organization"));

        verify(organizationService, times(1)).updateOrganization(anyLong(), any(OrganizationDTO.class));
    }

    @Test
    void getAllOrganizations() throws Exception {
        when(organizationService.getAllOrganization(anyInt(), anyInt(), any(Sort.class))).thenReturn(List.of(organizationDTO));

        mockMvc.perform(get("/api/organizations")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].organizationName").value("Organization"));

        verify(organizationService, times(1)).getAllOrganization(anyInt(), anyInt(), any(Sort.class));
    }

    @Test
    void deleteOrganization() throws Exception {
        doNothing().when(organizationService).deleteOrganization(anyLong());

        mockMvc.perform(delete("/api/organizations/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(organizationService, times(1)).deleteOrganization(anyLong());
    }
}
