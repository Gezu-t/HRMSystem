package et.hrms.controller;

import et.hrms.dal.dto.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EmployeeController {

    EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployeeList(@RequestParam int page, @RequestParam int size);


    List<EmployeeDTO> findEmployeeByDepartmentName(@PathVariable String departmentName,
                                                   @PathVariable int page,
                                                   @PathVariable int size);


}
