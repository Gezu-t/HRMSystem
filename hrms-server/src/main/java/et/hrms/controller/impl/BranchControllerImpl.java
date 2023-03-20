package et.hrms.controller.impl;


import et.hrms.controller.BranchController;
import et.hrms.dal.dto.BranchDTO;
import et.hrms.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchControllerImpl implements BranchController {


    private final BranchService branchService;

    @Override
    @PostMapping("/{organizationId}")
    public ResponseEntity<List<BranchDTO>> createBranch(@PathVariable long organizationId, @RequestBody BranchDTO branchDTO) {
        List<BranchDTO> result = branchService.createBranch(organizationId, branchDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{branchId}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable long branchId) {
        BranchDTO branchDTO = branchService.getDetailOfBranchById(branchId);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);
    }

    @Override
    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable long branchId, @RequestBody BranchDTO branchDTO) {
        BranchDTO updatedBranch = branchService.updateBranch(branchId, branchDTO);
        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranchInformation(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        List<BranchDTO> branchInformation = branchService.getAllBranchInformation(page, size);
        return new ResponseEntity<>(branchInformation, HttpStatus.OK);
    }

}
