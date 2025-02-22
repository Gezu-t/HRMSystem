package com.gtltek.employee.core.controller.branch.impl;

import com.gtltek.employee.core.controller.branch.BranchController;
import com.gtltek.employee.core.dal.dto.branch.BranchDTO;
import com.gtltek.employee.core.service.branch.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/branches")
public class BranchControllerImpl implements BranchController {

    private final BranchService branchService;

    @Override
    @PostMapping("/{organizationId}")
    public ResponseEntity<Void> createBranch(
            @PathVariable Long organizationId,
            @RequestBody BranchDTO branchDTOS) {
        branchService.createBranch(organizationId, branchDTOS);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getDetailOfBranchById(@PathVariable Long id) {
        BranchDTO branchDTO = branchService.getDetailOfBranchById(id);
        return ResponseEntity.ok(branchDTO);
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long branchId, @RequestBody BranchDTO branchDTO) {
        BranchDTO updatedBranch = branchService.updateBranch(branchId, branchDTO);
        return ResponseEntity.ok(updatedBranch);
    }


    @Override
    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranchInformation(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        List<BranchDTO> branches = branchService.getAllBranchInformation(page, size, sort);
        return ResponseEntity.ok(branches);
    }


    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<Page<BranchDTO>> getBranchesByOrganizationId(
            @PathVariable Long organizationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {
        String[] sortParams = sort.split(",");
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.by(sortParams[0]).with(Sort.Direction.fromString(sortParams[1]))));
        Page<BranchDTO> branches = branchService.getBranchesByOrganizationId(organizationId, pageable);
        return ResponseEntity.ok(branches);
    }

}
