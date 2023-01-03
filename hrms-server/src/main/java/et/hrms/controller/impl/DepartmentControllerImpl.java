package et.hrms.controller.impl;

import et.hrms.controller.DepartmentController;
import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.model.Department;
import et.hrms.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentControllerImpl implements DepartmentController {


    private final DepartmentService departmentService;


    public DepartmentControllerImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping("/add")
    public Department createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.createDepartment(departmentDTO);
    }


    @GetMapping("/getAll")
    public List<DepartmentDTO> getAllDepartment() {
        return departmentService.getAllDepartment();
    }
}
