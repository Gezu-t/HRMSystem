package et.hrms.controller.impl;

import et.hrms.controller.DepartmentController;
import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {


    private final DepartmentService departmentService;


    @Override
    @PostMapping(value = "/add/{branchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<DepartmentDTO> createDepartmentByBranchId(@PathVariable Long branchId,
                                                @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.createDepartmentByBranchId(branchId, departmentDTO);
    }

    @Override
    @PostMapping(value = "/add/{orgName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Set<DepartmentDTO> createDepartmentByOrganizationId(@PathVariable Long organizationId,
                                                @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.createDepartmentByOrganizationId(organizationId, departmentDTO);
    }


    @Override
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentDTO> getAllDepartment(@RequestParam("page") int page, @RequestParam("size") int size) {
        return departmentService.getAllDepartment(page, size);
    }

    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id,
                                                          @RequestBody DepartmentDTO departmentDTO) {
        if (departmentService.getDepartmentById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentDTO.setDepartmentId(id);
        return new ResponseEntity<>(departmentService.updateDepartment(departmentDTO), HttpStatus.OK);
    }
}
