package branch;

import et.hrms.dal.dto.department.BranchAddressDTO;
import et.hrms.dal.dto.department.BranchDTO;
import et.hrms.dal.mapping.department.BranchAddressMapper;
import et.hrms.dal.mapping.department.BranchMapper;
import et.hrms.dal.model.department.Branch;
import et.hrms.dal.model.department.BranchAddress;
import et.hrms.dal.model.department.Organization;
import et.hrms.dal.repository.structure.BranchAddressRepository;
import et.hrms.dal.repository.structure.BranchRepository;
import et.hrms.dal.repository.structure.OrganizationRepository;
import et.hrms.service.department.impl.BranchServiceImpl;
import et.hrms.service.log.AuditService;
import et.hrms.service.log.LogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

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
  private BranchAddressMapper branchAddressMapper;

  @Mock
  private BranchAddressRepository branchAddressRepository;

  @Mock
  private LogService logService;

  private BranchDTO branchDTO;
  private BranchAddressDTO branchAddressDTO;
  private Branch branch;
  private Organization organization;
  private BranchAddress branchAddress;

  @BeforeEach
  void setUp() {
    branchAddressDTO = new BranchAddressDTO();
    branchAddressDTO.setId(1L);
    branchAddressDTO.setTelNumberHome("123456789");
    branchAddressDTO.setTelNumberOffice("987654321");
    branchAddressDTO.setMobile("123456789");

    branchDTO = new BranchDTO();
    branchDTO.setId(1L);
    branchDTO.setBranchCode("BR001");
    branchDTO.setBranchName("Updated Branch Name");
    branchDTO.setBranchAddressDTO(branchAddressDTO);
    branchDTO.setOrganizationId(1L);

    branchAddress = new BranchAddress();
    branchAddress.setId(1L);
    branchAddress.setTelNumberHome("123456789");
    branchAddress.setTelNumberOffice("987654321");
    branchAddress.setMobile("123456789");

    organization = new Organization();
    organization.setId(1L);
    organization.setOrganizationName("Organization");

    branch = new Branch();
    branch.setId(1L);
    branch.setBranchCode("BR001");
    branch.setBranchName("Main Branch");
    branch.setBranchAddress(branchAddress);
    branch.setOrganization(organization);
  }

  @Test
  void createBranch() {
    // Mock repository and mapper behavior
    when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));
    when(branchMapper.toBranch(any(BranchDTO.class))).thenReturn(branch);
    when(branchAddressMapper.toBranchAddress(any(BranchAddressDTO.class))).thenReturn(branchAddress);
    when(branchAddressRepository.save(any(BranchAddress.class))).thenReturn(branchAddress);
    when(branchRepository.save(any(Branch.class))).thenReturn(branch);

    // Call the service method
    branchService.createBranch(1L, List.of(branchDTO));

    // Verify interactions with the repositories and services
    verify(organizationRepository).findById(anyLong());
    verify(branchMapper).toBranch(any(BranchDTO.class));
    verify(branchAddressMapper).toBranchAddress(any(BranchAddressDTO.class));
    verify(branchAddressRepository).save(any(BranchAddress.class));
    verify(branchRepository).save(any(Branch.class));
    verify(auditService, times(1)).logAction(any(), any(), any(), any());
  }


  @Test
  void getDetailOfBranchById() {
    when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));
    when(branchMapper.toBranchDTO(any(Branch.class))).thenReturn(branchDTO);
    when(branchAddressMapper.toBranchAddressDTO(any(BranchAddress.class))).thenReturn(branchAddressDTO);

    BranchDTO result = branchService.getDetailOfBranchById(1L);

    assertNotNull(result);
    assertEquals(branchDTO.getBranchName(), result.getBranchName());
    verify(branchRepository).findById(anyLong());
    verify(branchMapper).toBranchDTO(any(Branch.class));
    verify(branchAddressMapper).toBranchAddressDTO(any(BranchAddress.class));
  }

  @Test
  void updateBranch() {
    when(branchRepository.findById(eq(1L))).thenReturn(Optional.of(branch));
    when(branchAddressRepository.findById(eq(1L))).thenReturn(Optional.of(branchAddress));
    doAnswer(invocation -> {
      BranchDTO dto = invocation.getArgument(0);
      Branch entity = invocation.getArgument(1);
      entity.setBranchName(dto.getBranchName());  // Update branch name with the DTO's name
      return null;
    }).when(branchMapper).updateBranchFromDTO(any(BranchDTO.class), any(Branch.class));
    when(branchRepository.save(any(Branch.class))).thenReturn(branch);
    when(branchMapper.toBranchDTO(any(Branch.class))).thenReturn(branchDTO);

    BranchDTO updatedBranchDTO = branchService.updateBranch(1L, branchDTO);

    verify(branchRepository, times(1)).findById(eq(1L));
    verify(branchAddressRepository, times(1)).findById(eq(1L));
    verify(branchMapper, times(1)).updateBranchFromDTO(any(BranchDTO.class), any(Branch.class));
    verify(branchRepository, times(1)).save(any(Branch.class));
    verify(branchMapper, times(1)).toBranchDTO(any(Branch.class));

    assertEquals("Updated Branch Name", updatedBranchDTO.getBranchName());
  }

  @Test
  void getAllBranchInformation() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
    Page<Branch> branchPage = new PageImpl<>(List.of(branch), pageable, 1);

    when(branchRepository.findAll(any(Pageable.class))).thenReturn(branchPage);
    when(branchMapper.toBranchDTO(any(Branch.class))).thenReturn(branchDTO);

    List<BranchDTO> result = branchService.getAllBranchInformation(0, 10, Sort.by("id"));

    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(branchDTO.getBranchName(), result.get(0).getBranchName());
    verify(branchRepository).findAll(any(Pageable.class));
    verify(branchMapper).toBranchDTO(any(Branch.class));
    verify(logService).log(any());
  }

  @Test
  void deleteBranch() {
    when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));

    branchService.deleteBranch(1L);

    verify(branchRepository).findById(anyLong());
    verify(branchRepository).delete(any(Branch.class));
    verify(auditService).logAction(any(), any(), any(), any());
  }
}
