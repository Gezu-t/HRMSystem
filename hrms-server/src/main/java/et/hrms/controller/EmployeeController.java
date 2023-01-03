package et.hrms.controller;

import et.hrms.dal.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeController {
    @PostMapping("/add")
    void createEmployee(@RequestBody EmployeeDTO employeeDTO,
                        @RequestBody AppearanceDTO appearanceDTO,
                        @RequestBody FamilyDTO familyDTO,
                        @RequestBody List<DepartmentDTO> departmentDTOS,
                        @RequestBody List<AddressDTO> addressDTOS);

    @GetMapping("/getAll")
    List<EmployeeDTO> getListOfEmployee();
}
