package department;

import et.hrms.dal.dto.BranchDTO;
import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.mapping.DepartmentMapper;
import et.hrms.dal.model.Branch;
import et.hrms.dal.model.Department;
import et.hrms.dal.model.Organization;
import et.hrms.dal.repository.BranchRepository;
import et.hrms.dal.repository.DepartmentRepository;
import et.hrms.dal.repository.OrganizationRepository;
import et.hrms.service.AuditService;
import et.hrms.service.impl.DepartmentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {


    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private AuditService auditService;
    private Department department;
    private DepartmentDTO departmentDTO;
    private Branch branch;
    private BranchDTO branchDTO;
    private Organization organization;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


        department = new Department();
        department.setDepartmentName("software-development");
        department.setId(1L);

        branch = new Branch();
        branch.setId(1L);
        branch.setBranchName("Test Branch");
        branch.setBranchCode("BR001");
        department.setBranch(branch);


        branchDTO = new BranchDTO();
        branchDTO.setBranchId(1L);
        branchDTO.setBranchCode("BR001");
        branchDTO.setBranchName("Test Branch");
        branchDTO.setOrganizationId(1L);


        organization = new Organization();
        organization.setId(1L);
        branch.setOrganization(organization);
        department.setOrganization(organization);

    }

    @Test
    public void testGetDepartmentById() {
        // Prepare test data and mock interactions
        Department department = new Department();
        department.setId(1L);

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(1L);

        when(departmentMapper.toDepartmentDTO(any(Department.class))).thenReturn(departmentDTO);

        // Call the method under test
        DepartmentDTO result = departmentService.getDepartmentById(1L);

        // Verify the results
        assertNotNull(result);
        assertEquals(1L, result.getDepartmentId());
    }

    // Test for updateDepartment
    @Test
    public void testUpdateDepartment() {
        // Prepare test data and mock interactions
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("Test Department");
        department.setUpdatedAt(LocalDateTime.now());

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(new Branch()));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(1L);
        departmentDTO.setDepartmentName("Updated Department");

        when(departmentMapper.toDepartmentDTO(any(Department.class))).thenReturn(departmentDTO);

        // Call the method under test
        DepartmentDTO result = departmentService.updateDepartment(departmentDTO);

        // Verify the results
        assertNotNull(result);
        assertEquals(1L, result.getDepartmentId());
        assertEquals("Updated Department", result.getDepartmentName());
    }

}
