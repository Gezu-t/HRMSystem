package et.hrms.controller.employee;

import et.hrms.dal.dto.employee.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EmployeeController {


    @PostMapping
    ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO);

    @GetMapping("/{id}")
    ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<EmployeeDTO>> getAllEmployeeList(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size);

    @GetMapping("/department")
    ResponseEntity<List<EmployeeDTO>> findEmployeeByDepartmentName(@RequestParam String departmentName,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size);
}
