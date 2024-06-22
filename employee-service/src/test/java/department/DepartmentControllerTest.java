package department;

import et.hrms.controller.structure.impl.DepartmentControllerImpl;
import et.hrms.dal.dto.department.DepartmentUnderBranchDTO;
import et.hrms.dal.dto.department.DepartmentUnderOrganizationDTO;
import et.hrms.service.department.DepartmentService;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @InjectMocks
    private DepartmentControllerImpl departmentController;

    @Mock
    private DepartmentService departmentService;

    private MockMvc mockMvc;
    private DepartmentUnderBranchDTO departmentUnderBranchDTO;
    private DepartmentUnderOrganizationDTO departmentUnderOrganizationDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();

        departmentUnderBranchDTO = new DepartmentUnderBranchDTO();
        departmentUnderBranchDTO.setId(1L);
        departmentUnderBranchDTO.setDepartmentName("IT");
        departmentUnderBranchDTO.setLocations("Location");

        departmentUnderOrganizationDTO = new DepartmentUnderOrganizationDTO();
        departmentUnderOrganizationDTO.setId(1L);
        departmentUnderOrganizationDTO.setDepartmentName("IT");
        departmentUnderOrganizationDTO.setLocations("Location");
    }

    @Test
    void createDepartmentByBranchId() throws Exception {
        mockMvc.perform(post("/api/departments/branch/{branchId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"departmentName\": \"IT\", \"locations\": \"Location\"}]"))
                .andExpect(status().isCreated());

        verify(departmentService, times(1)).createDepartmentByBranchId(anyLong(), anyList());
    }

    @Test
    void createDepartmentByOrganizationId() throws Exception {
        mockMvc.perform(post("/api/departments/organization/{organizationId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"departmentName\": \"IT\", \"locations\": \"Location\"}]"))
                .andExpect(status().isCreated());

        verify(departmentService, times(1)).createDepartmentByOrganizationId(anyLong(), anyList());
    }

    @Test
    void getDepartmentUnderBranchById() throws Exception {
        when(departmentService.getDepartmentUnderBranchById(anyLong())).thenReturn(departmentUnderBranchDTO);

        mockMvc.perform(get("/api/departments/branch/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(departmentService, times(1)).getDepartmentUnderBranchById(anyLong());
    }

    @Test
    void getDepartmentUnderOrganizationById() throws Exception {
        when(departmentService.getDepartmentUnderOrganizationById(anyLong())).thenReturn(departmentUnderOrganizationDTO);

        mockMvc.perform(get("/api/departments/organization/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(departmentService, times(1)).getDepartmentUnderOrganizationById(anyLong());
    }

    @Test
    void updateDepartmentUnderBranch() throws Exception {
        when(departmentService.updateDepartmentUnderBranch(anyLong(), any(DepartmentUnderBranchDTO.class))).thenReturn(departmentUnderBranchDTO);

        mockMvc.perform(put("/api/departments/branch/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departmentName\": \"Updated IT\", \"locations\": \"Location\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(departmentService, times(1)).updateDepartmentUnderBranch(anyLong(), any(DepartmentUnderBranchDTO.class));
    }

    @Test
    void updateDepartmentUnderOrganization() throws Exception {
        when(departmentService.updateDepartmentUnderOrganization(anyLong(), any(DepartmentUnderOrganizationDTO.class))).thenReturn(departmentUnderOrganizationDTO);

        mockMvc.perform(put("/api/departments/organization/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departmentName\": \"Updated IT\", \"locations\": \"Location\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(departmentService, times(1)).updateDepartmentUnderOrganization(anyLong(), any(DepartmentUnderOrganizationDTO.class));
    }

    @Test
    void getDepartmentByBranch() throws Exception {
        when(departmentService.getDepartmentByBranch(anyLong(), any(Sort.class))).thenReturn(List.of(departmentUnderBranchDTO));

        mockMvc.perform(get("/api/departments/branch")
                        .param("branchId", "1")
                        .param("sort", "id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departmentName").value("IT"));

        verify(departmentService, times(1)).getDepartmentByBranch(anyLong(), any(Sort.class));
    }

    @Test
    void getDepartmentByOrganization() throws Exception {
        when(departmentService.getDepartmentByOrganization(anyLong(), any(Sort.class))).thenReturn(List.of(departmentUnderOrganizationDTO));

        mockMvc.perform(get("/api/departments/organization")
                        .param("organizationId", "1")
                        .param("sort", "id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departmentName").value("IT"));

        verify(departmentService, times(1)).getDepartmentByOrganization(anyLong(), any(Sort.class));
    }

    @Test
    void deleteDepartmentUnderBranch() throws Exception {
        mockMvc.perform(delete("/api/departments/branch/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(departmentService, times(1)).deleteDepartmentUnderBranch(anyLong());
    }

    @Test
    void deleteDepartmentUnderOrganization() throws Exception {
        mockMvc.perform(delete("/api/departments/organization/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(departmentService, times(1)).deleteDepartmentUnderOrganization(anyLong());
    }
}
