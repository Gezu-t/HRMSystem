package branch;

import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.controller.structure.impl.BranchControllerImpl;
import et.hrms.dal.dto.department.BranchDTO;
import et.hrms.dal.dto.department.BranchAddressDTO;
import et.hrms.dal.mapping.department.BranchMapper;
import et.hrms.dal.repository.structure.BranchRepository;
import et.hrms.service.department.BranchService;
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
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
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

  @Mock
  private BranchRepository branchRepository;

  @Mock
  private BranchMapper branchMapper;

  @Mock
  private final ObjectMapper objectMapper = new ObjectMapper();

  // Test data
  private BranchDTO testBranchDTO;
  private BranchDTO testBranchDTO2;
  private List<BranchDTO> branchDTOS;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(branchController).build();

    // Initialize test data
    testBranchDTO = new BranchDTO();
    testBranchDTO.setId(1L);
    testBranchDTO.setBranchCode("BR-001");
    testBranchDTO.setBranchName("Test Branch");
    testBranchDTO.setOrganizationId(1L);

    testBranchDTO2 = new BranchDTO();
    testBranchDTO2.setId(2L);
    testBranchDTO2.setBranchCode("BR-002");
    testBranchDTO2.setBranchName("COS-MO");
    testBranchDTO2.setOrganizationId(1L);

    BranchAddressDTO branchAddressDTO = new BranchAddressDTO();
    branchAddressDTO.setId(1L);
    branchAddressDTO.setCity("Cosenza");
    branchAddressDTO.setCountry("Italy");
    branchAddressDTO.setRegion("Calabria");
    branchAddressDTO.setProvince("Cosenza");

    testBranchDTO.setBranchAddressDTO(branchAddressDTO);
    testBranchDTO2.setBranchAddressDTO(branchAddressDTO);
    branchDTOS = new ArrayList<>();
    branchDTOS.add(testBranchDTO);
    branchDTOS.add(testBranchDTO2);
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
  void createBranch() throws Exception {
    // Mock the branchService
    Long id = 1L;
    doNothing().when(branchService).createBranch(eq(id), anyList());

    // Act & Assert
    mockMvc.perform(post("/api/branches/{organizationId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(Collections.singletonList(testBranchDTO))))
            .andExpect(status().isCreated());
  }

  @Test
  void getDetailOfBranchById() throws Exception {
    // Set up the mocks
    when(branchService.getDetailOfBranchById(testBranchDTO.getId())).thenReturn(testBranchDTO);

    // Call the getDetailOfBranchById endpoint
    mockMvc.perform(get("/api/branches/{branchId}", testBranchDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.branchCode").value(testBranchDTO.getBranchCode()))
            .andExpect(jsonPath("$.branchName").value(testBranchDTO.getBranchName()))
            .andExpect(jsonPath("$.organizationId").value(testBranchDTO.getOrganizationId()));
  }

  @Test
  void updateBranch() throws Exception {
    long branchId = 1L;

    BranchDTO branchDTO = new BranchDTO();
    branchDTO.setBranchName("Updated Branch Name");

    BranchDTO updatedBranchDTO = new BranchDTO();
    updatedBranchDTO.setBranchName("Updated Branch Name");

    when(branchService.updateBranch(eq(branchId), any(BranchDTO.class))).thenReturn(updatedBranchDTO);

    mockMvc.perform(put("/api/branches/{branchId}", branchId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(branchDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.branchName").value("Updated Branch Name"));

    verify(branchService, times(1)).updateBranch(eq(branchId), any(BranchDTO.class));
  }




  @Test
  void getAllBranchInformation() throws Exception {
    // Create some mock data
    List<BranchDTO> branchDTOs = new ArrayList<>();
    branchDTOs.add(testBranchDTO);
    branchDTOs.add(testBranchDTO2);

    // Mocking the service method to return the list of branch DTOs
    when(branchService.getAllBranchInformation(eq(0), eq(10), any(Sort.class))).thenReturn(branchDTOs);

    // Perform the request
    mockMvc.perform(get("/api/branches")
                    .param("page", "0")
                    .param("size", "10")
                    .param("sort", "id,asc"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].branchCode").value("BR-001"))
            .andExpect(jsonPath("$[1].branchCode").value("BR-002"))
            .andReturn();
  }

  @Test
  void deleteBranch() throws Exception {
    mockMvc.perform(delete("/api/branches/{id}", 1L))
            .andExpect(status().isNoContent());

    verify(branchService, times(1)).deleteBranch(anyLong());
  }
}
