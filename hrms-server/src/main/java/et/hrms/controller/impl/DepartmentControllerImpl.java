package et.hrms.controller.impl;

import et.hrms.controller.DepartmentController;
import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentService departmentService;

    @Override
    @PostMapping("/branch/{branchId}")
    public ResponseEntity<Void> createDepartmentByBranchId(@PathVariable Long branchId,
                                                          @RequestBody List<DepartmentDTO> departmentDTOs) {
         departmentService.createDepartmentByBranchId(branchId, departmentDTOs);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/organization/{organizationId}")
    public ResponseEntity<Void> createDepartmentByOrganizationId(
            @PathVariable Long organizationId,
            @RequestBody @Valid List<DepartmentDTO> departmentCreateRequests) {
       departmentService.createDepartmentByOrganizationId(
                organizationId, departmentCreateRequests);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public List<DepartmentDTO> getAllDepartment(@RequestParam("page") int page, @RequestParam("size") int size) {
        return departmentService.getAllDepartment(page, size);
    }

    @Override
    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long departmentId,
                                                          @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updateDepartmentDTO = departmentService.updateDepartment(departmentId, departmentDTO);
        return  ResponseEntity.ok(updateDepartmentDTO);
    }

    @Override
    @GetMapping("/{organizationId}")
    public List<DepartmentDTO> getDepartmentByOrganization(@PathVariable Long organizationId, @RequestParam Sort sort) {
        return departmentService.getDepartmentByOrganization(organizationId, sort);
    }

    @Override
    @GetMapping("/{branchId}")
    public List<DepartmentDTO> getDepartmentByBranch(Long branchId, Sort sort) {
        return departmentService.getDepartmentByBranch(branchId, sort);
    }

}
