package et.hrms.controller.impl;

import et.hrms.controller.EmployeeController;
import et.hrms.dal.dto.*;
import et.hrms.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;




    @Override
    @PostMapping(value= "/add", produces = MediaType.APPLICATION_JSON_VALUE )
    public void createEmployee(@RequestBody EmployeeDTO employeeDTO,
                               @RequestBody AppearanceDTO appearanceDTO,
                               @RequestBody FamilyDTO familyDTO,
                               @RequestBody List<DepartmentDTO> departmentDTOS,
                               @RequestBody List<AddressDTO> addressDTOS,
                               @RequestBody List<EducationDTO> educationDTOS) {


        employeeService.createEmployee(employeeDTO, familyDTO, appearanceDTO, departmentDTOS, addressDTOS, educationDTOS);

    }



    @Override
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDTO> getListOfEmployee() {
        return employeeService.getListOfEmployee();
    }



    @Override
    @Operation(description = "get employee by using id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    @Operation(description = "get employee by using employee number")
    @GetMapping(value = "/{employeeNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> getEmployeeByEmployeeNumber(@PathVariable String employeeNo) {
        EmployeeDTO employee = employeeService.getEmployeeByEmployeeNo(employeeNo);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
