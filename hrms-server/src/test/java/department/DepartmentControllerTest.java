package department;


import et.hrms.controller.impl.DepartmentControllerImpl;
import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.service.DepartmentService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {
    @InjectMocks
    private DepartmentControllerImpl departmentController;
    @Mock
    private DepartmentService departmentService;
    private DepartmentDTO departmentDTO;
    private List<DepartmentDTO> departmentDTOList;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
        departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(1L);
        departmentDTO.setLocations("Milan, Cosenza, Tirana");
        departmentDTO.setDepartmentName("Software Engineering");
        departmentDTO.setBranchId(100L);

        departmentDTOList = new ArrayList<>();
        departmentDTOList.add(departmentDTO);

    }

    @Test
    public void testCreateDepartmentByOrganizationId() throws Exception {
        when(departmentService.createDepartmentByOrganizationId(anyLong(), anyList()))
                .thenReturn(departmentDTOList);

        mockMvc.perform(post("/api/departments/organization/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"departmentId\":1,\"departmentName\":\"Sample Department\",\"branchId\":1,\"organizationId\":1}]"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"departmentId\":1,\"departmentName\":\"Sample Department\",\"branchId\":1,\"organizationId\":1}]"));

        verify(departmentService, times(1)).createDepartmentByOrganizationId(anyLong(), anyList());
    }

    @Test
    public void testGetAllDepartment() throws Exception {
        when(departmentService.getAllDepartment(anyInt(), anyInt()))
                .thenReturn(departmentDTOList);

        mockMvc.perform(get("/api/departments/all")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"departmentId\":1,\"departmentName\":\"Sample Department\",\"branchId\":1,\"organizationId\":1}]"));

        verify(departmentService, times(1)).getAllDepartment(anyInt(), anyInt());
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        when(departmentService.getDepartmentById(anyLong())).thenReturn(departmentDTO);
        when(departmentService.updateDepartment(any(DepartmentDTO.class))).thenReturn(departmentDTO);

        mockMvc.perform(put("/api/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departmentId\":1,\"departmentName\":\"Sample Department\",\"branchId\":1,\"organizationId\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"departmentId\":1,\"departmentName\":\"Sample Department\",\"branchId\":1,\"organizationId\":1}"));

        verify(departmentService, times(1)).getDepartmentById(anyLong());
        verify(departmentService, times(1)).updateDepartment(any(DepartmentDTO.class));
    }

    @Test
    public void testGetDepartmentByOrganization() throws Exception {
        when(departmentService.getDepartmentByOrganization(anyLong(), any(Sort.class)))
                .thenReturn(departmentDTOList);

        mockMvc.perform(get("/api/departments/organization/1")
                        .param("sort", "departmentId,asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"departmentId\":1,\"departmentName\":\"Sample Department\",\"branchId\":1,\"organizationId\":1}]"));

        verify(departmentService, times(1)).getDepartmentByOrganization(anyLong(), any(Sort.class));
    }

    @Test
    public void testGetDepartmentByBranch() throws Exception {
        when(departmentService.getDepartmentByBranch(anyLong(), any(Sort.class)))
                .thenReturn(departmentDTOList);

        mockMvc.perform(get("/api/departments/branch/1")
                        .param("sort", "departmentId,asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"departmentId\":1,\"departmentName\":\"Sample Department\",\"branchId\":1,\"organizationId\":1}]"));

        verify(departmentService, times(1)).getDepartmentByBranch(anyLong(), any(Sort.class));
    }

}