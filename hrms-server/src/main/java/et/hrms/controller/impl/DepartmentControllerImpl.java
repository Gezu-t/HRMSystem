package et.hrms.controller.impl;

import et.hrms.controller.DepartmentController;
import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.model.Department;
import et.hrms.service.DepartmentService;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {


    private final DepartmentService departmentService;



    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody DepartmentDTO departmentDTO,
                                       @RequestBody OrganizationDTO organizationDTO) throws Exception {


        return departmentService.createDepartment(departmentDTO, organizationDTO);
    }




    @GetMapping("/all")
    public List<DepartmentDTO> getAllDepartment(@RequestParam("page") int page, @RequestParam("size") int size) {
        return departmentService.getAllDepartment(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id,
                                                              @RequestBody DepartmentDTO departmentDTO) throws Exception {
        if (departmentService.getDepartmentById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentDTO.setDepartmentId(id);
        return new ResponseEntity<>(departmentService.updateDepartment(departmentDTO), HttpStatus.OK);
    }
}
