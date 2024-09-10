package employee;


import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeAddressDTO;
import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeDTO;
import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeDetailDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.department.DepartmentMapper;
import com.hrmsystem.employeeprofileservice.dal.mapping.employee.EmployeeAddressMapper;
import com.hrmsystem.employeeprofileservice.dal.mapping.employee.EmployeeDetailMapper;
import com.hrmsystem.employeeprofileservice.dal.mapping.employee.EmployeeMapper;
import com.hrmsystem.employeeprofileservice.service.employee.impl.EmployeeServiceImpl;
import dal.model.employee.Employee;
import dal.model.employee.EmployeeDetail;
import dal.repository.employee.EmployeeDetailRepository;
import dal.repository.employee.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeAddressMapper addressMapper;

    @Mock
    private EmployeeDetailMapper employeeDetailMapper;

    @Mock
    private EmployeeDetailRepository employeeDetailRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    // Test case for createEmployee method
    @Test
    public void testCreateEmployee() {
        // Given
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = new Employee();
        employee.setId(1L);

        List<EmployeeDetailDTO> employeeDetailDTOS = new ArrayList<>();
        employeeDTO.setEmployeeDetails(employeeDetailDTOS);

        List<EmployeeAddressDTO> employeeAddressDTOS = new ArrayList<>();
        employeeDTO.setEmployeeAddresses(employeeAddressDTOS);

        Mockito.when(employeeMapper.toEmployee(employeeDTO)).thenReturn(employee);
//        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        Mockito.when(employeeMapper.toEmployeeDTO(employee)).thenReturn(employeeDTO);

        // When
        EmployeeDTO createdEmployeeDTO = employeeService.createEmployee(employeeDTO);

        // Then
        Assert.assertNotNull(createdEmployeeDTO);
        assertEquals(employeeDTO, createdEmployeeDTO);
    }

    // Test case for findEmployeeByDepartmentName method
    @Test
    public void testFindEmployeeByDepartmentName() {
        // Given
        String departmentName = "IT";
        int page = 0;
        int size = 10;

        List<EmployeeDetail> employeeDetails = new ArrayList<>();
        EmployeeDetail employeeDetail = new EmployeeDetail();
        Employee employee = new Employee();
        employee.setId(1L);
        employeeDetail.setEmployee(employee);
        employeeDetails.add(employeeDetail);

        Mockito.when(employeeDetailRepository.findEmployeeByDepartmentName(departmentName, PageRequest.of(page, size))).thenReturn(employeeDetails);
        Mockito.when(employeeMapper.toEmployeeDTO(employee)).thenReturn(new EmployeeDTO());

        // When
        List<EmployeeDTO> employees = employeeService.findEmployeeByDepartmentName(departmentName, page, size);

        // Then
        Assert.assertNotNull(employees);
        Assert.assertFalse(employees.isEmpty());
    }

    // Test case for getAllEmployeeList method
    @Test
    public void testGetAllEmployeeList() {
        // Given
        int page = 0;
        int size = 10;

        Page<Employee> employees = new PageImpl<>(Arrays.asList(new Employee()));

        Mockito.when(employeeRepository.findAll(PageRequest.of(page, size))).thenReturn(employees);
        Mockito.when(employeeMapper.toEmployeeDTO(Mockito.any())).thenReturn(new EmployeeDTO());

        // When
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployeeList(page, size);

        // Then
        Assert.assertNotNull(employeeDTOs);
        Assert.assertFalse(employeeDTOs.isEmpty());
    }

    // Test case for getEmployeeById method
    // Test case for getEmployeeById method
    // Test case for getEmployeeById method
    // Test case for getEmployeeById method
    @Test
    public void testGetEmployeeById() {
        // Given
        Long id = 1L;

        Employee employee = new Employee();
        employee.setId(id);

        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        Mockito.when(employeeMapper.toEmployeeDTO(employee)).thenReturn(new EmployeeDTO());
//        Mockito.when(departmentMapper.toDepartmentDTO(Mockito.any())).thenReturn(new DepartmentDTO());
//        Mockito.when(employeeDetailMapper.toEmployeeDetailDTO(Mockito.any())).thenReturn(new EmployeeDetailDTO());
//        Mockito.when(addressMapper.toEmployeeAddressDTO(Mockito.any())).thenReturn(new EmployeeAddressDTO());

        // When
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);

        // Then
        Assert.assertNotNull(employeeDTO);
//        Assert.assertEquals(employeeDTO.getEmployeeId(), id);
    }




}





