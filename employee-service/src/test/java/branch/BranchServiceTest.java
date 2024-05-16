package branch;

import et.hrms.dal.dto.structure.BranchDTO;
import et.hrms.dal.dto.structure.OrganizationAddressDTO;
import et.hrms.dal.mapping.BranchMapper;
import et.hrms.dal.mapping.OrganizationAddressMapper;
import et.hrms.dal.model.structure.Branch;
import et.hrms.dal.model.structure.Organization;
import et.hrms.dal.model.structure.OrganizationAddress;
import et.hrms.dal.repository.structure.BranchRepository;
import et.hrms.dal.repository.structure.OrganizationAddressRepository;
import et.hrms.dal.repository.structure.OrganizationRepository;
import et.hrms.service.log.AuditService;
import et.hrms.service.log.LogService;
import et.hrms.service.structure.BranchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BranchServiceTest {
  @InjectMocks
  private BranchServiceImpl branchService;

  @Mock
  private BranchMapper branchMapper;

  @Mock
  private BranchRepository branchRepository;

  @Mock
  private OrganizationRepository organizationRepository;

  @Mock
  private AuditService auditService;

  @Mock
  private OrganizationAddressMapper organizationAddressMapper;

  @Mock
  private OrganizationAddressRepository organizationAddressRepository;

  @Mock
  private LogService logService;

  // Test data
  private Organization testOrganization;
  private OrganizationAddressDTO organizationAddressDTO;
  private BranchDTO testBranchDTO;
  private Branch testBranch;
  private OrganizationAddress testOrganizationAddress;

  @BeforeEach
  void setUp() {
    // Initialize test data
    testOrganization = new Organization();
    testOrganization.setId(1L);

    testBranchDTO = new BranchDTO();
    testBranchDTO.setOrganizationId(testOrganization.getId());


    testBranch = new Branch();
    testBranch.setId(2L);
    testBranch.setOrganization(testOrganization);

    testOrganizationAddress = new OrganizationAddress();
    testOrganizationAddress.setId(2L);
    testOrganizationAddress.setBranch(testBranch);
    testBranch.setOrganizationAddress(testOrganizationAddress);
    testOrganizationAddress.setBranch(testBranch);
    organizationAddressDTO = new OrganizationAddressDTO();
    organizationAddressDTO.setAddressId(2L);

    testBranchDTO.setOrganizationAddressDTO(organizationAddressDTO);
  }

  @Test
  void createBranch() {
    // Set up the mocks
    when(organizationRepository.findById(testOrganization.getId())).thenReturn(Optional.of(testOrganization));
    when(branchMapper.toBranch(testBranchDTO)).thenReturn(testBranch);
    when(organizationAddressMapper.toOrganizationAddress(any())).thenReturn(testOrganizationAddress);
    when(branchRepository.saveAll(any())).thenReturn(Collections.singletonList(testBranch));

    // Call the createBranch method
    branchService.createBranch(testOrganization.getId(), List.of(testBranchDTO));

    // Verify the interactions
    verify(organizationRepository).findById(testOrganization.getId());
    verify(branchMapper).toBranch(testBranchDTO);
    verify(organizationAddressMapper).toOrganizationAddress(any());
    verify(branchRepository).saveAll(any());
    verify(auditService, times(1)).logAction(any(), any(), any(), any());
  }

  // Other test methods...
// Test for getDetailOfBranchById
  @Test
  void getDetailOfBranchById() {
    // Set up the mocks
    when(branchRepository.findById(testBranch.getId())).thenReturn(Optional.of(testBranch));
    when(organizationAddressMapper.toOrganizationAddressDTO(testOrganizationAddress)).thenReturn(new OrganizationAddressDTO());
    when(branchMapper.toBranchDTO(testBranch)).thenReturn(testBranchDTO);

    // Call the getDetailOfBranchById method
    BranchDTO resultBranchDTO = branchService.getDetailOfBranchById(testBranch.getId());

    // Verify the interactions
    verify(branchRepository).findById(testBranch.getId());
    verify(organizationAddressMapper).toOrganizationAddressDTO(testOrganizationAddress);
    verify(branchMapper).toBranchDTO(testBranch);

    // Assert the result
    assertEquals(testBranchDTO, resultBranchDTO);
  }

  // Test for updateBranch
  @Test
  void updateBranch() {
    // Set up the mocks
    Organization org = new Organization();
    org.setId(1L);
    when(branchRepository.findById(testBranch.getId())).thenReturn(Optional.of(testBranch));

    // Call the updateBranch method
    branchService.updateBranch(testBranch.getId(), testBranchDTO);

    // Verify the interactions
    verify(branchRepository).findById(testBranch.getId());
    verify(organizationAddressMapper).toOrganizationAddress(organizationAddressDTO);
    verify(branchRepository).save(testBranch);
    verify(auditService).logAction(any(), any(), any(), any());


  }

  // Test for getAllBranchInformation
  @Test
  void getAllBranchInformation() {
    // Set up the mocks
    Pageable pageable = PageRequest.of(0, 10, Sort.sort(BranchDTO.class));
    Page<Branch> branchesPage = new PageImpl<>(List.of(testBranch), pageable, 1);
    when(branchRepository.findAll(pageable)).thenReturn(branchesPage);
    when(branchMapper.toBranchDTO(testBranch)).thenReturn(testBranchDTO);

    // Call the getAllBranchInformation method
    List<BranchDTO> branchDTOList = branchService.getAllBranchInformation(0, 10,  Sort.sort(BranchDTO.class) );

    // Verify the interactions
    verify(branchRepository).findAll(pageable);
    verify(branchMapper).toBranchDTO(testBranch);
    verify(logService).log(any());

    // Assert the result
    assertEquals(1, branchDTOList.size());
    assertEquals(testBranchDTO, branchDTOList.get(0));
  }
}


