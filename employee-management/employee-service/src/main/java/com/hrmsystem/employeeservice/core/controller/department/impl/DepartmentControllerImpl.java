package com.hrmsystem.employeeservice.core.controller.department.impl;

import com.hrmsystem.employeeservice.core.dal.dto.department.DepartmentDTO;
import com.hrmsystem.employeeservice.core.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentControllerImpl {

    private final DepartmentService departmentService;

    // Create department under a specific branch
    @PostMapping("/branch/{branchId}")
    public ResponseEntity<DepartmentDTO> createDepartmentByBranch(
            @PathVariable Long branchId,
            @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO, branchId, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
    }

    // Create department under a specific organization
    @PostMapping("/organization/{organizationId}")
    public ResponseEntity<DepartmentDTO> createDepartmentByOrganization(
            @PathVariable Long organizationId,
            @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO, null, organizationId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
    }

    // Get a department by ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentDTO);
    }

    // Update a department by ID
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(id, departmentDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    // Get departments under a specific branch
    @GetMapping("/branch")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByBranch(
            @RequestParam Long branchId,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        List<DepartmentDTO> departments = departmentService.getDepartmentsByBranch(branchId, Sort.by(direction, sort));
        return ResponseEntity.ok(departments);
    }

    // Get departments under a specific organization
    @GetMapping("/organization")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByOrganization(
            @RequestParam Long organizationId,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        List<DepartmentDTO> departments = departmentService.getDepartmentsByOrganization(organizationId, Sort.by(direction, sort));
        return ResponseEntity.ok(departments);
    }

    // Get all departments with pagination and sorting
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        List<DepartmentDTO> departments = departmentService.getAllDepartments(page, size, Sort.by(direction, sort));
        return ResponseEntity.ok(departments);
    }

    // Delete a department by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
