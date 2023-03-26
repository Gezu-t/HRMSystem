package branch;

import et.hrms.dal.dto.BranchDTO;
import et.hrms.dal.dto.OrganizationAddressDTO;
import et.hrms.dal.mapping.BranchMapper;
import et.hrms.dal.mapping.OrganizationAddressMapper;
import et.hrms.dal.model.Branch;
import et.hrms.dal.model.Organization;
import et.hrms.dal.model.OrganizationAddress;
import et.hrms.dal.repository.BranchRepository;
import et.hrms.dal.repository.OrganizationAddressRepository;
import et.hrms.dal.repository.OrganizationRepository;
import et.hrms.service.AuditService;
import et.hrms.service.LogService;
import et.hrms.service.impl.BranchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

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

    @InjectMocks
    private BranchServiceImpl branchService;

    private Branch branch;
    private BranchDTO branchDTO;
    private OrganizationAddress organizationAddress;
    private Organization organization;

    @BeforeEach
    void setup() {

        branch = new Branch();
        branch.setId(1L);
        branch.setBranchName("Test Branch");
        branch.setBranchCode("BR001");

        OrganizationAddressDTO organizationAddressDTO = new OrganizationAddressDTO();
        organizationAddressDTO.setAddressId(2L);

        branchDTO = new BranchDTO();
        branchDTO.setBranchId(1L);
        branchDTO.setBranchCode("BR001");
        branchDTO.setBranchName("Test Branch");
        branchDTO.setOrganizationId(1L);
        branchDTO.setOrganizationAddressDTO(organizationAddressDTO);

        organizationAddress = new OrganizationAddress();
        organizationAddress.setId(2L);
        branch.setOrganizationAddress(organizationAddress);

        organization = new Organization();
        organization.setId(1L);
        branch.setOrganization(organization);

    }

    @Test
    void testCreateBranch() {
        long organizationId = 1L;
        BranchDTO branchDTO = new BranchDTO();
        Organization organization = new Organization();
        Branch branch = new Branch();
        OrganizationAddress organizationAddress = new OrganizationAddress();

        when(organizationRepository.findById(organizationId)).thenReturn(Optional.of(organization));
        when(organizationAddressMapper.toOrganizationAddress(any())).thenReturn(organizationAddress);
        when(branchMapper.toBranch(branchDTO)).thenReturn(branch);
        when(branchRepository.saveAll(any())).thenReturn(Collections.singletonList(branch));
        when(branchMapper.toBranchDTOs(any())).thenReturn(Collections.singletonList(branchDTO));

        List<BranchDTO> result = branchService.createBranch(organizationId, branchDTO);
        assertEquals(1, result.size());
        assertEquals(branchDTO, result.get(0));
    }

    @Test
    void testGetBranchById() {
        long branchId = 1L;
        Branch branch = new Branch();
        Organization organization = new Organization();
        OrganizationAddress organizationAddress = new OrganizationAddress();
        organizationAddress.setId(1L);
        organization.setId(1L);
        branch.setOrganizationAddress(organizationAddress);
        branch.setOrganization(organization);
        BranchDTO branchDTO = new BranchDTO();
        OrganizationAddressDTO organizationAddressDTO = new OrganizationAddressDTO();

        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branch));
        when(branchMapper.toBranchDTO(branch)).thenReturn(branchDTO);
        when(organizationAddressMapper.toOrganizationAddressDTO(organizationAddress)).thenReturn(organizationAddressDTO);

        BranchDTO result = branchService.getDetailOfBranchById(branchId);
        assertEquals(branchDTO, result);
    }

    @Test
    void updateBranch_success() {
        // Arrange
        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));
        when(organizationAddressRepository.findById(anyLong())).thenReturn(Optional.of(organizationAddress));
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));
        when(branchMapper.toBranchDTO(any(Branch.class))).thenReturn(branchDTO);
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        // Act
        BranchDTO updatedBranchDTO = branchService.updateBranch(1L, branchDTO);

        // Assert
        assertEquals(branchDTO.getBranchId(), updatedBranchDTO.getBranchId());
        assertEquals(branchDTO.getBranchCode(), updatedBranchDTO.getBranchCode());
        assertEquals(branchDTO.getBranchName(), updatedBranchDTO.getBranchName());

        verify(branchRepository, times(1)).findById(1L);
        verify(organizationAddressRepository, times(1)).findById(2L);
        verify(organizationRepository, times(1)).findById(1L);
        verify(branchMapper, times(1)).toBranchDTO(branch);
        verify(branchRepository, times(1)).save(branch);
        verify(auditService, times(1)).logAction("username", "Branch", "Update", 1L);
    }

    @Test
    void testGetAllBranchInformation() {
        int page = 0;
        int size = 10;
        Branch branch = new Branch();
        Page<Branch> branchPage = new PageImpl<>(Collections.singletonList(branch));
        BranchDTO branchDTO = new BranchDTO();

        when(branchRepository.findAll(PageRequest.of(page, size))).thenReturn(branchPage);
        when(branchMapper.toBranchDTO(branch)).thenReturn(branchDTO);
        doNothing().when(logService).log(anyString());

        List<BranchDTO> result = branchService.getAllBranchInformation(page, size);
        assertEquals(1, result.size());
        assertEquals(branchDTO, result.get(0));

        verify(logService, times(1)).log(any());
    }
}
