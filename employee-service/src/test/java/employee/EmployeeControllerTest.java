package employee;

import et.hrms.controller.employee.imp.EmployeeControllerImpl;
import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.service.employee.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class EmployeeControllerTest {

    @InjectMocks
    private EmployeeControllerImpl employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void createEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        when(employeeService.createEmployee(employeeDTO)).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.createEmployee(employeeDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
        verify(employeeService).createEmployee(any(EmployeeDTO.class));
    }

    @Test
    void getEmployeeById() {
        Long id = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        when(employeeService.getEmployeeById(id)).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
        verify(employeeService).getEmployeeById(id);
    }

    @Test
    void getAllEmployeeList() {
        int page = 0;
        int size = 10;
        List<EmployeeDTO> employeeDTOList = List.of(new EmployeeDTO());
        when(employeeService.getAllEmployeeList(page, size)).thenReturn(employeeDTOList);

        ResponseEntity<List<EmployeeDTO>> response = employeeController.getAllEmployeeList(page, size);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTOList, response.getBody());
        verify(employeeService).getAllEmployeeList(page, size);
    }

    @Test
    void findEmployeeByDepartmentName() {
        String departmentName = "IT";
        int page = 0;
        int size = 10;
        List<EmployeeDTO> employeeDTOList = List.of(new EmployeeDTO());
        when(employeeService.findEmployeeByDepartmentName(departmentName, page, size)).thenReturn(employeeDTOList);

        ResponseEntity<List<EmployeeDTO>> response = employeeController.findEmployeeByDepartmentName(departmentName, page, size);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTOList, response.getBody());
        verify(employeeService).findEmployeeByDepartmentName(departmentName, page, size);
    }
}

