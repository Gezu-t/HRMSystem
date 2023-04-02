package branch;

import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.controller.impl.BranchControllerImpl;
import et.hrms.dal.dto.BranchDTO;
import et.hrms.dal.dto.OrganizationAddressDTO;
import et.hrms.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BranchControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BranchControllerImpl branchController;

    @Mock
    private BranchService branchService;

    private ObjectMapper objectMapper = new ObjectMapper();

    // Test data
    private BranchDTO testBranchDTO;
    private OrganizationAddressDTO organizationAddressDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(branchController).build();

        // Initialize test data
        testBranchDTO = new BranchDTO();
        testBranchDTO.setBranchId(1L);
        testBranchDTO.setBranchCode("BR-001");
        testBranchDTO.setBranchName("Test Branch");
        testBranchDTO.setOrganizationId(1L);
        organizationAddressDTO = new OrganizationAddressDTO();
        organizationAddressDTO.setAddressId(1L);
        testBranchDTO.setOrganizationAddressDTO(organizationAddressDTO);
    }

    @Test
    void createBranch() throws Exception {
        // Call the createBranch endpoint

        String branchDTOSJson = objectMapper.writeValueAsString(Collections.singletonList(testBranchDTO));

        mockMvc.perform(post("/api/branches/organization/{organizationId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(branchDTOSJson))
                .andExpect(status().isCreated());
    }

    @Test
    void getDetailOfBranchById() throws Exception {
        // Set up the mocks
        when(branchService.getDetailOfBranchById(testBranchDTO.getBranchId())).thenReturn(testBranchDTO);

        // Call the getDetailOfBranchById endpoint
        mockMvc.perform(get("/api/branches/{branchId}", testBranchDTO.getBranchId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.branchCode").value(testBranchDTO.getBranchCode()))
                .andExpect(jsonPath("$.branchName").value(testBranchDTO.getBranchName()))
                .andExpect(jsonPath("$.organizationId").value(testBranchDTO.getOrganizationId()));
    }

    @Test
    void updateBranch() throws Exception {
        // Set up the mocks
        when(branchService.updateBranch(testBranchDTO.getBranchId(), testBranchDTO)).thenReturn(testBranchDTO);

        // Call the updateBranch endpoint
        String branchDTOJson = objectMapper.writeValueAsString(testBranchDTO);

        mockMvc.perform(put("/api/branches/{branchId}", testBranchDTO.getBranchId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(branchDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.branchCode").value(testBranchDTO.getBranchCode()))
                .andExpect(jsonPath("$.branchName").value(testBranchDTO.getBranchName()))
                .andExpect(jsonPath("$.organizationId").value(testBranchDTO.getOrganizationId()));
    }

    @Test
    void getAllBranchInformation() throws Exception {
        // Set up the mocks
        when(branchService.getAllBranchInformation(0, 10)).thenReturn(List.of(testBranchDTO));

        // Call the getAllBranchInformation endpoint
        mockMvc.perform(get("/api/branches")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].branchCode").value(testBranchDTO.getBranchCode()))
                .andExpect(jsonPath("$[0].branchName").value(testBranchDTO.getBranchName()))
                .andExpect(jsonPath("$[0].organizationId").value(testBranchDTO.getOrganizationId()));
    }
}
