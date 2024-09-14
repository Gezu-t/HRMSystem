package com.hrmsystem.employeeservice.core.controller.department.impl;

import com.hrmsystem.employeeservice.core.controller.department.DepartmentController;
import com.hrmsystem.employeeservice.core.dal.dto.department.DepartmentUnderBranchDTO;
import com.hrmsystem.employeeservice.core.dal.dto.department.DepartmentUnderOrganizationDTO;
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
public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentService departmentService;

    @Override
    @PostMapping("/branch/{branchId}")
    public ResponseEntity<Void> createDepartmentByBranchId(
            @PathVariable Long branchId,
            @RequestBody List<DepartmentUnderBranchDTO> departmentUnderBranchDTOS) {
        departmentService.createDepartmentByBranchId(branchId, departmentUnderBranchDTOS);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PostMapping("/organization/{organizationId}")
    public ResponseEntity<Void> createDepartmentByOrganizationId(
            @PathVariable Long organizationId,
            @RequestBody List<DepartmentUnderOrganizationDTO> departmentUnderOrganizationDTOS) {
        departmentService.createDepartmentByOrganizationId(organizationId, departmentUnderOrganizationDTOS);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/branch/{id}")
    public ResponseEntity<DepartmentUnderBranchDTO> getDepartmentUnderBranchById(@PathVariable Long id) {
        DepartmentUnderBranchDTO departmentDTO = departmentService.getDepartmentUnderBranchById(id);
        return ResponseEntity.ok(departmentDTO);
    }

    @Override
    @GetMapping("/organization/{id}")
    public ResponseEntity<DepartmentUnderOrganizationDTO> getDepartmentUnderOrganizationById(@PathVariable Long id) {
        DepartmentUnderOrganizationDTO departmentDTO = departmentService.getDepartmentUnderOrganizationById(id);
        return ResponseEntity.ok(departmentDTO);
    }

    @Override
    @PutMapping("/branch/{id}")
    public ResponseEntity<DepartmentUnderBranchDTO> updateDepartmentUnderBranch(
            @PathVariable Long id,
            @RequestBody DepartmentUnderBranchDTO departmentUnderBranchDTO) {
        DepartmentUnderBranchDTO updatedDepartment = departmentService.updateDepartmentUnderBranch(id, departmentUnderBranchDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    @Override
    @PutMapping("/organization/{id}")
    public ResponseEntity<DepartmentUnderOrganizationDTO> updateDepartmentUnderOrganization(
            @PathVariable Long id,
            @RequestBody DepartmentUnderOrganizationDTO departmentUnderOrganizationDTO) {
        DepartmentUnderOrganizationDTO updatedDepartment = departmentService.updateDepartmentUnderOrganization(id, departmentUnderOrganizationDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    @Override
    @GetMapping("/branch")
    public ResponseEntity<List<DepartmentUnderBranchDTO>> getDepartmentByBranch(
            @RequestParam Long branchId,
            @RequestParam(defaultValue = "id") String sort) {
        List<DepartmentUnderBranchDTO> departments = departmentService.getDepartmentByBranch(branchId, Sort.by(sort));
        return ResponseEntity.ok(departments);
    }

    @Override
    @GetMapping("/organization")
    public ResponseEntity<List<DepartmentUnderOrganizationDTO>> getDepartmentByOrganization(
            @RequestParam Long organizationId,
            @RequestParam(defaultValue = "id") String sort) {
        List<DepartmentUnderOrganizationDTO> departments = departmentService.getDepartmentByOrganization(organizationId, Sort.by(sort));
        return ResponseEntity.ok(departments);
    }

    @Override
    @DeleteMapping("/branch/{id}")
    public ResponseEntity<Void> deleteDepartmentUnderBranch(@PathVariable Long id) {
        departmentService.deleteDepartmentUnderBranch(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/organization/{id}")
    public ResponseEntity<Void> deleteDepartmentUnderOrganization(@PathVariable Long id) {
        departmentService.deleteDepartmentUnderOrganization(id);
        return ResponseEntity.noContent().build();
    }
}
