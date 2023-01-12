package et.hrms.controller;

import et.hrms.dal.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeController {




    void createEmployee(@RequestBody EmployeeDTO employeeDTO,
                        @RequestBody AppearanceDTO appearanceDTO,
                        @RequestBody FamilyDTO familyDTO,
                        @RequestBody List<DepartmentDTO> departmentDTOS,
                        @RequestBody List<AddressDTO> addressDTOS,
                        @RequestBody List<EducationDTO> educationDTOS);


    List<EmployeeDTO> getListOfEmployee();

    ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id);

    ResponseEntity<EmployeeDTO> getEmployeeByEmployeeNumber(@PathVariable String employeeNo);
}
