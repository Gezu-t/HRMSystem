package et.hrms.controller.impl;

import et.hrms.controller.DepartmentController;
import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {


    private final DepartmentService departmentService;


    @Override
    @PostMapping(value = "/add/{branchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<DepartmentDTO> createDepartmentByBranchId(@PathVariable Long branchId,
                                                          @RequestBody List<DepartmentDTO> departmentDTO) {
        return departmentService.createDepartmentByBranchId(branchId, departmentDTO);
    }

    @PostMapping("/organization/{organizationId}")
    public ResponseEntity<List<DepartmentDTO>> createDepartmentByOrganizationId(
            @PathVariable Long organizationId,
            @RequestBody @Valid List<DepartmentDTO> departmentCreateRequests) {
        List<DepartmentDTO> departmentDTOs = departmentService.createDepartmentByOrganizationId(
                organizationId, departmentCreateRequests);
        return ResponseEntity.ok(departmentDTOs);
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


    @Override
    @GetMapping(path = "/organization/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentDTO> getDepartmentByOrganization(@PathVariable Long organizationId, @RequestParam Sort sort) {
        return departmentService.getDepartmentByOrganization(organizationId, sort);
    }



    @Override
    @GetMapping(path = "/branch/{branchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentDTO> getDepartmentByBranch(Long branchId, Sort sort) {
        return departmentService.getDepartmentByBranch(branchId, sort);
    }

}
