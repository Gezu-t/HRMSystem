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
import et.hrms.service.impl.AuditServiceImpl;
import et.hrms.service.impl.DepartmentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;



@RunWith(MockitoJUnitRunner.class)
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
    private AuditServiceImpl auditService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("Sales");

        DepartmentDTO expectedDepartmentDTO = new DepartmentDTO();
        expectedDepartmentDTO.setDepartmentId(1L);
        expectedDepartmentDTO.setDepartmentName("Sales");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(departmentMapper.toDepartmentDTO(department)).thenReturn(expectedDepartmentDTO);

        // Act
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(1L);

        // Assert
        assertNotNull(departmentDTO);
        assertEquals(expectedDepartmentDTO.getDepartmentId(), departmentDTO.getDepartmentId());
        assertEquals(expectedDepartmentDTO.getDepartmentName(), departmentDTO.getDepartmentName());

        verify(departmentRepository, times(1)).findById(1L);
        verify(departmentMapper, times(1)).toDepartmentDTO(department);


    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("Sales");

        Branch branch = new Branch();
        branch.setId(1L);
        branch.setBranchName("Test Branch");
        branch.setBranchCode("BR001");

        DepartmentDTO updatedDepartmentDTO = new DepartmentDTO();
        updatedDepartmentDTO.setDepartmentId(1L);
        updatedDepartmentDTO.setDepartmentName("Updated Department");
        updatedDepartmentDTO.setBranchId(1L);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        when(departmentMapper.toDepartmentDTO(any(Department.class))).thenReturn(updatedDepartmentDTO);

        // Call the method under test
        DepartmentDTO result = departmentService.updateDepartment(updatedDepartmentDTO);

        // Verify the results
        assertNotNull(result);
        assertEquals(1L, result.getDepartmentId());
        assertEquals("Updated Department", result.getDepartmentName());
    }

    @Test
    public void testCreateDepartmentByBranchId() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("Sales");

        Branch branch = new Branch();
        branch.setId(1L);
        branch.setBranchName("Test Branch");
        branch.setBranchCode("BR001");

        department.setBranch(branch);
        DepartmentDTO depDTO = new DepartmentDTO();
        depDTO.setDepartmentId(1L);
        depDTO.setDepartmentName("Software Development");
        depDTO.setBranchId(1L);

        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
        departmentDTOs.add(depDTO);

        // Prepare test data and mock interactions
        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));
        when(departmentMapper.toDepartment(any(DepartmentDTO.class))).thenReturn(department);
        when(departmentRepository.saveAll(anyList())).thenReturn(List.of(department));
        // Call the method under test
        List<DepartmentDTO> result = departmentService.createDepartmentByBranchId(branch.getId(), departmentDTOs);

        // Verify the results
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void testCreateDepartmentByOrganizationId() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("Sales");

        Organization organization = new Organization();
        organization.setId(1L);

        DepartmentDTO departmentDTO1 = new DepartmentDTO();
        departmentDTO1.setDepartmentName("IT");
//        departmentDTO1.setDescription("Information Technology");

        DepartmentDTO departmentDTO2 = new DepartmentDTO();
        departmentDTO2.setDepartmentName("HR");
//        departmentDTO2.setDescription("Human Resources");

        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
        departmentDTOs.add(departmentDTO1);
        departmentDTOs.add(departmentDTO2);

        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));
        when(departmentMapper.toDepartment(any(DepartmentDTO.class))).thenReturn(department);
        when(departmentRepository.saveAll(anyList())).thenReturn(List.of(department));

        List<DepartmentDTO> result = departmentService.createDepartmentByOrganizationId(organization.getId(), departmentDTOs);

        // Verify the results
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }


    @Test
    public void testGetDepartmentByOrganization() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("Sales");

        Organization organization = new Organization();
        organization.setId(1L);
        organization.setOrganizationName("Test Organization");
        organization.setEstablishmentDate(LocalDate.of(2020, 1, 1));
        organization.setOwnerName("John Doe");
        department.setOrganization(organization);

        DepartmentDTO depDTO = new DepartmentDTO();
        depDTO.setDepartmentId(1L);
        depDTO.setDepartmentName("Software Development");
        depDTO.setBranchId(1L);

        Sort sort = Sort.by(Sort.Direction.ASC, "departmentName");
        when(departmentRepository.findByOrganizationId(anyLong(), eq(sort))).thenReturn(List.of(department));
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(1L);
        departmentDTO.setDepartmentName("Test Department");
        departmentDTO.setLocations("Test Location");
        when(departmentMapper.toDepartmentDTO(any(Department.class))).thenReturn(departmentDTO);
        // Call the method under test
        List<DepartmentDTO> result = departmentService.getDepartmentByOrganization(1L, Sort.by(Sort.Direction.ASC, "departmentName"));
        // Verify the results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Department", result.get(0).getDepartmentName());
        assertEquals("Test Location", result.get(0).getLocations());
    }
//
//    @Test
//    public void testGetDepartmentByBranch() {
//        BranchDTO branchDTO = new BranchDTO();
//        branchDTO.setBranchName("Test Branch");
//        branchDTO.setBranchId(1L);
//        Branch branch = new Branch();
//        branch.setId(1L);
//        branch.setBranchName("Test Branch");
//        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
//        for (int i = 1; i <= 3; i++) {
//            DepartmentDTO departmentDTO = new DepartmentDTO();
//            departmentDTO.setDepartmentName("Test Department " + i);
//            departmentDTOs.add(departmentDTO);
//        }
//        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
////        departmentService.createDepartmentByBranchId(branchDTO.getBranchId(), departmentDTOs);
//
//        List<DepartmentDTO> foundDepartments = departmentService.createDepartmentByBranchId(1L, departmentDTOs);
//
//        assertEquals(departmentDTOs.size(), foundDepartments.size());
//        for (int i = 0; i < departmentDTOs.size(); i++) {
//            DepartmentDTO expectedDepartment = departmentDTOs.get(i);
//            expectedDepartment.setBranchId(1L + i);
//            DepartmentDTO actualDepartment = foundDepartments.get(i);
//            actualDepartment.setBranchId(1L + i);
//
//            assertEquals(expectedDepartment.getDepartmentName(), actualDepartment.getDepartmentName());
//            assertEquals(branchDTO.getBranchId(), actualDepartment.getBranchId());
//        }
//    }

    @Test
    public void testGetAllDepartment() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("Sales");

        Organization organization = new Organization();
        organization.setId(1L);
        organization.setOrganizationName("Test Organization");
        organization.setEstablishmentDate(LocalDate.of(2020, 1, 1));
        organization.setOwnerName("John Doe");
        department.setOrganization(organization);

        DepartmentDTO depDTO = new DepartmentDTO();
        depDTO.setDepartmentId(1L);
        depDTO.setDepartmentName("Software Development");
        depDTO.setBranchId(1L);

        // Prepare test data and mock interactions
        Page<Department> departmentPage = new PageImpl<>(Collections.singletonList(department));
        when(departmentRepository.findAll(any(Pageable.class))).thenReturn(departmentPage);
        when(departmentMapper.toDepartmentDTO(any(Department.class))).thenReturn(depDTO);
        // Call the method under test
        List<DepartmentDTO> result = departmentService.getAllDepartment(0, 10);
        // Verify the results
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}