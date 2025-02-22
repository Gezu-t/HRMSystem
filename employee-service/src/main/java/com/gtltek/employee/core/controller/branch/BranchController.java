package com.gtltek.employee.core.controller.branch;

import com.gtltek.employee.core.dal.dto.branch.BranchDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BranchController {

    ResponseEntity<Void> createBranch(
            @PathVariable Long organizationId,
            @RequestBody BranchDTO branchDTOS);
    ResponseEntity<BranchDTO> getDetailOfBranchById(@PathVariable Long id);
    ResponseEntity<BranchDTO> updateBranch(
            @PathVariable Long id,
            @RequestBody BranchDTO branchDTO);
    ResponseEntity<List<BranchDTO>> getAllBranchInformation(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "branchId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir);
    ResponseEntity<Void> deleteBranch(@PathVariable Long id);

    @GetMapping("/organization/{organizationId}")
    ResponseEntity<Page<BranchDTO>> getBranchesByOrganizationId(
            @PathVariable Long organizationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "branchId,asc") String sort);
}
