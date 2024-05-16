package et.hrms.controller.employee.imp;

import et.hrms.controller.employee.EmployeeController;
import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.service.employee.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {
    private final EmployeeService employeeService;

    @Override
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        if (employeeDTO == null) {
            return ResponseEntity.notFound().build(); // Proper handling of not found scenario
        }
        return ResponseEntity.ok(employeeDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployeeList(page, size);
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }

    @Override
    @GetMapping("/by-department")
    public ResponseEntity<List<EmployeeDTO>> findEmployeeByDepartmentName(
            @RequestParam String departmentName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<EmployeeDTO> employeeDTOs = employeeService.findEmployeeByDepartmentName(departmentName, page, size);
        return ResponseEntity.ok(employeeDTOs);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> searchEmployees(@RequestParam String name) {
        List<EmployeeDTO> employees = employeeService.searchEmployeesByName(name);
        return ResponseEntity.ok(employees);
    }
}
