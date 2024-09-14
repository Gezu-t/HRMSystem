package com.hrmsystem.employeeservice.core.controller.department;

import com.hrmsystem.employeeservice.core.dal.dto.department.DepartmentUnderBranchDTO;
import com.hrmsystem.employeeservice.core.dal.dto.department.DepartmentUnderOrganizationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface DepartmentController {

    @PostMapping("/branch/{branchId}")
    ResponseEntity<Void> createDepartmentByBranchId(
            @PathVariable Long branchId,
            @RequestBody List<DepartmentUnderBranchDTO> departmentUnderBranchDTOS);

    @PostMapping("/organization/{organizationId}")
    ResponseEntity<Void> createDepartmentByOrganizationId(
            @PathVariable Long organizationId,
            @RequestBody List<DepartmentUnderOrganizationDTO> departmentUnderOrganizationDTOS);

    @GetMapping("/branch/{id}")
    ResponseEntity<DepartmentUnderBranchDTO> getDepartmentUnderBranchById(@PathVariable Long id);

    @GetMapping("/organization/{id}")
    ResponseEntity<DepartmentUnderOrganizationDTO> getDepartmentUnderOrganizationById(@PathVariable Long id);

    @PutMapping("/branch/{id}")
    ResponseEntity<DepartmentUnderBranchDTO> updateDepartmentUnderBranch(
            @PathVariable Long id,
            @RequestBody DepartmentUnderBranchDTO departmentUnderBranchDTO);

    @PutMapping("/organization/{id}")
    ResponseEntity<DepartmentUnderOrganizationDTO> updateDepartmentUnderOrganization(
            @PathVariable Long id,
            @RequestBody DepartmentUnderOrganizationDTO departmentUnderOrganizationDTO);

    @GetMapping("/branch")
    ResponseEntity<List<DepartmentUnderBranchDTO>> getDepartmentByBranch(
            @RequestParam Long branchId,
            @RequestParam(defaultValue = "id") String sort);

    @GetMapping("/organization")
    ResponseEntity<List<DepartmentUnderOrganizationDTO>> getDepartmentByOrganization(
            @RequestParam Long organizationId,
            @RequestParam(defaultValue = "id") String sort);

    @DeleteMapping("/branch/{id}")
    ResponseEntity<Void> deleteDepartmentUnderBranch(@PathVariable Long id);

    @DeleteMapping("/organization/{id}")
    ResponseEntity<Void> deleteDepartmentUnderOrganization(@PathVariable Long id);
}
