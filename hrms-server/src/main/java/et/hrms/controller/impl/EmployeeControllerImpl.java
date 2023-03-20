package et.hrms.controller.impl;

import et.hrms.controller.EmployeeController;
import et.hrms.dal.dto.*;
import et.hrms.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;


    @Override
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);

    }


    @Override
    @GetMapping(value = "/all/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDTO> getAllEmployeeList(@RequestParam int page, @RequestParam int size) {
        return employeeService.getAllEmployeeList(page, size);
    }


    @Override
    @Operation(description = "List of Employee based on the department name")
    @GetMapping(value = "/employeeList/{departmentName}/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<EmployeeDTO> findEmployeeByDepartmentName(@PathVariable String departmentName,
                                                          @PathVariable int page,
                                                          @PathVariable int size) {
        return employeeService.findEmployeeByDepartmentName(departmentName, page, size);
    }
//
//    @Override
//    @Operation(description = "get employee by using employee number")
//    @GetMapping(value = "/{employeeNo}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<EmployeeDTO> getEmployeeByEmployeeNumber(@PathVariable String employeeNo) {
//        EmployeeDTO employee = employeeService.getEmployeeByEmployeeNo(employeeNo);
//        if (employee == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(employee, HttpStatus.OK);
//    }

}
