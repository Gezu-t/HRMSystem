package et.hrms.controller.employee;

import et.hrms.controller.employee.EmployeeController;
import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {
    private final EmployeeService employeeService;

    @Override
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeList(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployeeList(page, size);
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }

    @Override
    @GetMapping("/department")
    public ResponseEntity<List<EmployeeDTO>> findEmployeeByDepartmentName(@RequestParam String departmentName,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        List<EmployeeDTO> employeeDTOs = employeeService.findEmployeeByDepartmentName(departmentName, page, size);
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }
}
