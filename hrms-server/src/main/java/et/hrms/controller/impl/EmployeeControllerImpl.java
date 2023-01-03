package et.hrms.controller.impl;

import et.hrms.controller.EmployeeController;
import et.hrms.dal.dto.*;
import et.hrms.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeControllerImpl implements EmployeeController {

//    private final Logger logger = LoggerFactory.getLogger(EmployeeControllerImpl.class);
    private final EmployeeService employeeService;


    @Override
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody EmployeeDTO employeeDTO,
                               @RequestBody AppearanceDTO appearanceDTO,
                               @RequestBody FamilyDTO familyDTO,
                               @RequestBody List<DepartmentDTO> departmentDTOS,
                               @RequestBody List<AddressDTO> addressDTOS) {

        employeeService.createEmployee(employeeDTO, familyDTO, appearanceDTO, departmentDTOS, addressDTOS);

    }

    @Override
    @GetMapping("/getAll")
    @Operation(summary="Get all employee")
    public List<EmployeeDTO> getListOfEmployee() {
        return employeeService.getListOfEmployee();
    }
}
