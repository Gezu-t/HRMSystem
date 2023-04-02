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
    @PostMapping("/organization/{organizationId}")
    public ResponseEntity<Void> createBranch(@PathVariable long organizationId, @RequestBody List<BranchDTO> branchDTOS) {
        branchService.createBranch(organizationId, branchDTOS);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{branchId}")
    public ResponseEntity<BranchDTO> getDetailOfBranchById(@PathVariable long branchId) {
        BranchDTO branchDTO = branchService.getDetailOfBranchById(branchId);
        return ResponseEntity.ok(branchDTO);
    }

    @Override
    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable long branchId,
                                                  @RequestBody BranchDTO branchDTO) {
        BranchDTO updatedBranchDTO = branchService.updateBranch(branchId, branchDTO);
        return ResponseEntity.ok(updatedBranchDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranchInformation(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<BranchDTO> branchDTOList = branchService.getAllBranchInformation(page, size);
        return ResponseEntity.ok(branchDTOList);
    }

}
