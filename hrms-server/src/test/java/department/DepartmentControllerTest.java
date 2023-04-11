package department;


import et.hrms.controller.impl.DepartmentControllerImpl;
import et.hrms.dal.dto.BranchDTO;
import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.service.BranchService;
import et.hrms.service.DepartmentService;
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
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private DepartmentControllerImpl departmentController;
    @Mock
    private DepartmentService departmentService;
    @Mock
    private BranchService branchService;

    private DepartmentDTO departmentDTO;
    private List<DepartmentDTO> departmentDTOList;


    private com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
    private BranchDTO testBranchDTO;
   private OrganizationDTO organizationDTO;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
        departmentDTO = new DepartmentDTO();

        departmentDTO.setDepartmentId(1L);
        departmentDTO.setLocations("Milan, Cosenza, Tirana");
        departmentDTO.setDepartmentName("Software Engineering");

        departmentDTOList = new ArrayList<>();
        departmentDTOList.add(departmentDTO);

        testBranchDTO = new BranchDTO();
        testBranchDTO.setBranchId(1L);

        departmentDTO.setBranchId(testBranchDTO.getBranchId());

        organizationDTO = new OrganizationDTO();
        organizationDTO.setOrganizationId(1L);
        departmentDTO.setOrganizationId(organizationDTO.getOrganizationId());


    }


    @Test
    public void testCreateDepartmentByBranchId() throws Exception {
        String departmentDTOSJson = objectMapper.writeValueAsString(Collections.singletonList(departmentDTO));

        mockMvc.perform(post("/api/departments/branch/{branchId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentDTOSJson))
                .andExpect(status().isCreated());

    }
    @Test
    public void testCreateDepartmentByOrganizationId() throws Exception {
        String departmentDTOSJson = objectMapper.writeValueAsString(Collections.singletonList(departmentDTO));

        mockMvc.perform(post("/api/departments/organization/{organizationId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentDTOSJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllDepartment() throws Exception {
      // Set up the mocks
      when(departmentService.getAllDepartment(0, 10)).thenReturn(List.of(departmentDTO));

      // Call the get all department endpoint
      mockMvc.perform(get("/api/departments")
                      .param("page", "0")
                      .param("size", "10"))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$[0].departmentName").value(departmentDTO.getDepartmentName()))
              .andExpect(jsonPath("$[0].locations").value(departmentDTO.getLocations()))
              .andExpect(jsonPath("$[0].organizationId").value(departmentDTO.getOrganizationId()));
    }

    @Test
    public void testUpdateDepartment() throws Exception {
      when(departmentService.updateDepartment(anyLong(), any())).thenReturn(departmentDTO);
      // Call the updateDepartment endpoint
      String departmentDTOJson = objectMapper.writeValueAsString(departmentDTO);

      mockMvc.perform(put("/api/departments/{departmentId}", departmentDTO.getDepartmentId())
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(departmentDTOJson))
                      .andExpect(status().isOk());
    }

//    @Test
//    public void testGetDepartmentByOrganization() throws Exception {
//        when(departmentService.getDepartmentByOrganization(anyLong(), any(Sort.class)))
//                .thenReturn(departmentDTOList);
//
//        mockMvc.perform(get("/api/departments/organization/1")
//                        .param("sort", "departmentId,asc"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json("[{" +
//                        "\"departmentId\":1," +
//                        "\"departmentName\":\"Sample Department\"," +
//                        "\"branchId\":1,\"organizationId\":1}]"));
//
//        verify(departmentService, times(1)).getDepartmentByOrganization(anyLong(), any(Sort.class));
//    }
//
//    @Test
//    public void testGetDepartmentByBranch() throws Exception {
//
//        // Set up the mocks
//        when(branchService.getDetailOfBranchById(testBranchDTO.getBranchId())).thenReturn(testBranchDTO);
//
//
//        // Call the getDetailOfBranchById endpoint
//        mockMvc.perform(get("/api/branches/{branchId}", testBranchDTO.getBranchId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.branchCode").value(testBranchDTO.getBranchCode()))
//                .andExpect(jsonPath("$.branchName").value(testBranchDTO.getBranchName()))
//                .andExpect(jsonPath("$.organizationId").value(testBranchDTO.getOrganizationId()));
//
//
//
//        when(departmentService.getDepartmentByBranch(anyLong(), any(Sort.class)))
//                .thenReturn(departmentDTOList);
//
//        mockMvc.perform(get("/api/departments/branch/1")
//                        .param("sort", "departmentId,asc"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json("[{" +
//                        "\"departmentId\":1," +
//                        "\"departmentName\":\"Sample Department\"," +
//                        "\"branchId\":1,\"organizationId\":1}]"));
//
//        verify(departmentService, times(1)).getDepartmentByBranch(anyLong(), any(Sort.class));
//    }

}