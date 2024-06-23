package department;

import com.hrmsystem.employeeprofileservice.dal.dto.department.DepartmentUnderBranchDTO;
import com.hrmsystem.employeeprofileservice.dal.dto.department.DepartmentUnderOrganizationDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.department.DepartmentUnderBranchMapper;
import com.hrmsystem.employeeprofileservice.dal.mapping.department.DepartmentUnderOrganizationMapper;
import com.hrmsystem.employeeprofileservice.service.department.impl.DepartmentServiceImpl;
import com.hrmsystem.employeeprofileservice.service.log.AuditService;
import com.hrmsystem.employeeprofileservice.service.log.LogService;
import com.hrmsystem.employeeservice.core.dal.model.branch.Branch;
import com.hrmsystem.employeeservice.core.dal.model.department.DepartmentUnderBranch;
import com.hrmsystem.employeeservice.core.dal.model.department.DepartmentUnderOrganization;
import com.hrmsystem.employeeservice.core.dal.model.organization.Organization;
import com.hrmsystem.employeeservice.core.dal.repository.branch.BranchRepository;
import com.hrmsystem.employeeservice.core.dal.repository.department.DepartmentUnderBranchRepository;
import com.hrmsystem.employeeservice.core.dal.repository.department.DepartmentUnderOrganizationRepository;
import com.hrmsystem.employeeservice.core.dal.repository.organization.OrganizationRepository;
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
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentUnderBranchRepository departmentUnderBranchRepository;

    @Mock
    private DepartmentUnderOrganizationRepository departmentUnderOrganizationRepository;

    @Mock
    private DepartmentUnderBranchMapper departmentUnderBranchMapper;

    @Mock
    private DepartmentUnderOrganizationMapper departmentUnderOrganizationMapper;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private AuditService auditService;

    @Mock
    private LogService logService;

    private DepartmentUnderBranchDTO departmentUnderBranchDTO;
    private DepartmentUnderOrganizationDTO departmentUnderOrganizationDTO;
    private DepartmentUnderBranch departmentUnderBranch;
    private DepartmentUnderOrganization departmentUnderOrganization;
    private Branch branch;
    private Organization organization;

    @BeforeEach
    void setUp() {
        departmentUnderBranchDTO = new DepartmentUnderBranchDTO();
        departmentUnderBranchDTO.setId(1L);
        departmentUnderBranchDTO.setDepartmentName("IT");
        departmentUnderBranchDTO.setLocations("Location");

        departmentUnderOrganizationDTO = new DepartmentUnderOrganizationDTO();
        departmentUnderOrganizationDTO.setId(1L);
        departmentUnderOrganizationDTO.setDepartmentName("IT");
        departmentUnderOrganizationDTO.setLocations("Location");

        departmentUnderBranch = new DepartmentUnderBranch();
        departmentUnderBranch.setId(1L);
        departmentUnderBranch.setDepartmentName("IT");
        departmentUnderBranch.setLocations("Location");

        departmentUnderOrganization = new DepartmentUnderOrganization();
        departmentUnderOrganization.setId(1L);
        departmentUnderOrganization.setDepartmentName("IT");
        departmentUnderOrganization.setLocations("Location");

        branch = new Branch();
        branch.setId(1L);

        organization = new Organization();
        organization.setId(1L);
    }

    @Test
    void createDepartmentByBranchId() {
        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));
        when(departmentUnderBranchMapper.toDepartmentUnderBranch(any(DepartmentUnderBranchDTO.class))).thenReturn(departmentUnderBranch);
        when(departmentUnderBranchRepository.saveAll(anyList())).thenReturn(List.of(departmentUnderBranch));

        departmentService.createDepartmentByBranchId(1L, List.of(departmentUnderBranchDTO));

        verify(branchRepository).findById(anyLong());
        verify(departmentUnderBranchMapper).toDepartmentUnderBranch(any(DepartmentUnderBranchDTO.class));
        verify(departmentUnderBranchRepository).saveAll(anyList());
        verify(auditService, times(1)).logAction(any(), any(), any(), any());
    }

    @Test
    void createDepartmentByOrganizationId() {
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));
        when(departmentUnderOrganizationMapper.toDepartmentUnderOrganization(any(DepartmentUnderOrganizationDTO.class))).thenReturn(departmentUnderOrganization);
        when(departmentUnderOrganizationRepository.saveAll(anyList())).thenReturn(List.of(departmentUnderOrganization));

        departmentService.createDepartmentByOrganizationId(1L, List.of(departmentUnderOrganizationDTO));

        verify(organizationRepository).findById(anyLong());
        verify(departmentUnderOrganizationMapper).toDepartmentUnderOrganization(any(DepartmentUnderOrganizationDTO.class));
        verify(departmentUnderOrganizationRepository).saveAll(anyList());
        verify(auditService, times(1)).logAction(any(), any(), any(), any());
    }

    @Test
    void getDepartmentUnderBranchById() {
        when(departmentUnderBranchRepository.findById(anyLong())).thenReturn(Optional.of(departmentUnderBranch));
        when(departmentUnderBranchMapper.toDepartmentUnderBranchDTO(any(DepartmentUnderBranch.class))).thenReturn(departmentUnderBranchDTO);

        DepartmentUnderBranchDTO result = departmentService.getDepartmentUnderBranchById(1L);

        assertNotNull(result);
        assertEquals(departmentUnderBranchDTO.getDepartmentName(), result.getDepartmentName());
        verify(departmentUnderBranchRepository).findById(anyLong());
        verify(departmentUnderBranchMapper).toDepartmentUnderBranchDTO(any(DepartmentUnderBranch.class));
    }

    @Test
    void getDepartmentUnderOrganizationById() {
        when(departmentUnderOrganizationRepository.findById(anyLong())).thenReturn(Optional.of(departmentUnderOrganization));
        when(departmentUnderOrganizationMapper.toDepartmentUnderOrganizationDTO(any(DepartmentUnderOrganization.class))).thenReturn(departmentUnderOrganizationDTO);

        DepartmentUnderOrganizationDTO result = departmentService.getDepartmentUnderOrganizationById(1L);

        assertNotNull(result);
        assertEquals(departmentUnderOrganizationDTO.getDepartmentName(), result.getDepartmentName());
        verify(departmentUnderOrganizationRepository).findById(anyLong());
        verify(departmentUnderOrganizationMapper).toDepartmentUnderOrganizationDTO(any(DepartmentUnderOrganization.class));
    }

    @Test
    void updateDepartmentUnderBranch() {
        when(departmentUnderBranchRepository.findById(anyLong())).thenReturn(Optional.of(departmentUnderBranch));
        when(departmentUnderBranchRepository.save(any(DepartmentUnderBranch.class))).thenReturn(departmentUnderBranch);
        when(departmentUnderBranchMapper.toDepartmentUnderBranchDTO(any(DepartmentUnderBranch.class))).thenReturn(departmentUnderBranchDTO);

        departmentService.updateDepartmentUnderBranch(1L, departmentUnderBranchDTO);

        verify(departmentUnderBranchRepository).findById(anyLong());
        verify(departmentUnderBranchMapper).updateDepartmentUnderBranchFromDTO(any(DepartmentUnderBranchDTO.class), any(DepartmentUnderBranch.class));
        verify(departmentUnderBranchRepository).save(any(DepartmentUnderBranch.class));
        verify(auditService).logAction(any(), any(), any(), any());
    }

    @Test
    void updateDepartmentUnderOrganization() {
        when(departmentUnderOrganizationRepository.findById(anyLong())).thenReturn(Optional.of(departmentUnderOrganization));
        when(departmentUnderOrganizationRepository.save(any(DepartmentUnderOrganization.class))).thenReturn(departmentUnderOrganization);
        when(departmentUnderOrganizationMapper.toDepartmentUnderOrganizationDTO(any(DepartmentUnderOrganization.class))).thenReturn(departmentUnderOrganizationDTO);

        departmentService.updateDepartmentUnderOrganization(1L, departmentUnderOrganizationDTO);

        verify(departmentUnderOrganizationRepository).findById(anyLong());
        verify(departmentUnderOrganizationMapper).updateDepartmentUnderOrganizationFromDTO(any(DepartmentUnderOrganizationDTO.class), any(DepartmentUnderOrganization.class));
        verify(departmentUnderOrganizationRepository).save(any(DepartmentUnderOrganization.class));
        verify(auditService).logAction(any(), any(), any(), any());
    }

    @Test
    void getDepartmentByBranch() {
        when(departmentUnderBranchRepository.findByBranchId(anyLong(), any(Sort.class))).thenReturn(List.of(departmentUnderBranch));
        when(departmentUnderBranchMapper.toDepartmentUnderBranchDTO(any(DepartmentUnderBranch.class))).thenReturn(departmentUnderBranchDTO);

        List<DepartmentUnderBranchDTO> result = departmentService.getDepartmentByBranch(1L, Sort.by("id"));

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(departmentUnderBranchDTO.getDepartmentName(), result.get(0).getDepartmentName());
        verify(departmentUnderBranchRepository).findByBranchId(anyLong(), any(Sort.class));
        verify(departmentUnderBranchMapper).toDepartmentUnderBranchDTO(any(DepartmentUnderBranch.class));
    }

    @Test
    void getDepartmentByOrganization() {
        when(departmentUnderOrganizationRepository.findByOrganizationId(anyLong(), any(Sort.class))).thenReturn(List.of(departmentUnderOrganization));
        when(departmentUnderOrganizationMapper.toDepartmentUnderOrganizationDTO(any(DepartmentUnderOrganization.class))).thenReturn(departmentUnderOrganizationDTO);

        List<DepartmentUnderOrganizationDTO> result = departmentService.getDepartmentByOrganization(1L, Sort.by("id"));

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(departmentUnderOrganizationDTO.getDepartmentName(), result.get(0).getDepartmentName());
        verify(departmentUnderOrganizationRepository).findByOrganizationId(anyLong(), any(Sort.class));
        verify(departmentUnderOrganizationMapper).toDepartmentUnderOrganizationDTO(any(DepartmentUnderOrganization.class));
    }

    @Test
    void getAllDepartmentsUnderBranch() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<DepartmentUnderBranch> departmentPage = new PageImpl<>(List.of(departmentUnderBranch), pageable, 1);

        when(departmentUnderBranchRepository.findAll(any(Pageable.class))).thenReturn(departmentPage);
        when(departmentUnderBranchMapper.toDepartmentUnderBranchDTO(any(DepartmentUnderBranch.class))).thenReturn(departmentUnderBranchDTO);

        List<DepartmentUnderBranchDTO> result = departmentService.getAllDepartmentsUnderBranch(0, 10, Sort.by("id"));

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(departmentUnderBranchDTO.getDepartmentName(), result.get(0).getDepartmentName());
        verify(departmentUnderBranchRepository).findAll(any(Pageable.class));
        verify(departmentUnderBranchMapper).toDepartmentUnderBranchDTO(any(DepartmentUnderBranch.class));
        verify(logService).log(any());
    }

    @Test
    void getAllDepartmentsUnderOrganization() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<DepartmentUnderOrganization> departmentPage = new PageImpl<>(List.of(departmentUnderOrganization), pageable, 1);

        when(departmentUnderOrganizationRepository.findAll(any(Pageable.class))).thenReturn(departmentPage);
        when(departmentUnderOrganizationMapper.toDepartmentUnderOrganizationDTO(any(DepartmentUnderOrganization.class))).thenReturn(departmentUnderOrganizationDTO);

        List<DepartmentUnderOrganizationDTO> result = departmentService.getAllDepartmentsUnderOrganization(0, 10, Sort.by("id"));

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(departmentUnderOrganizationDTO.getDepartmentName(), result.get(0).getDepartmentName());
        verify(departmentUnderOrganizationRepository).findAll(any(Pageable.class));
        verify(departmentUnderOrganizationMapper).toDepartmentUnderOrganizationDTO(any(DepartmentUnderOrganization.class));
        verify(logService).log(any());
    }

    @Test
    void deleteDepartmentUnderBranch() {
        when(departmentUnderBranchRepository.findById(anyLong())).thenReturn(Optional.of(departmentUnderBranch));

        departmentService.deleteDepartmentUnderBranch(1L);

        verify(departmentUnderBranchRepository).findById(anyLong());
        verify(departmentUnderBranchRepository).delete(any(DepartmentUnderBranch.class));
        verify(auditService).logAction(any(), any(), any(), any());
    }

    @Test
    void deleteDepartmentUnderOrganization() {
        when(departmentUnderOrganizationRepository.findById(anyLong())).thenReturn(Optional.of(departmentUnderOrganization));

        departmentService.deleteDepartmentUnderOrganization(1L);

        verify(departmentUnderOrganizationRepository).findById(anyLong());
        verify(departmentUnderOrganizationRepository).delete(any(DepartmentUnderOrganization.class));
        verify(auditService).logAction(any(), any(), any(), any());
    }
}
