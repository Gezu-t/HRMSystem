package et.hrms.controller;

import et.hrms.dal.dto.BranchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BranchController {


 @PostMapping("/organization/{organizationId}")
 ResponseEntity<Void> createBranch(@PathVariable long organizationId, @RequestBody List<BranchDTO> branchDTOS);

 @GetMapping("/{branchId}")
 ResponseEntity<BranchDTO> getDetailOfBranchById(@PathVariable long branchId);

 @PutMapping("/{branchId}")
 ResponseEntity<BranchDTO> updateBranch(@PathVariable long branchId,
                                        @RequestBody BranchDTO branchDTO);

 @GetMapping
 ResponseEntity<List<BranchDTO>> getAllBranchInformation(
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(defaultValue = "10") int size);
}
