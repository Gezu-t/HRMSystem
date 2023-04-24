package et.hrms.controller.structure;

import et.hrms.dal.dto.structure.BranchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BranchController {


 ResponseEntity<Void> createBranch(@PathVariable long organizationId, @RequestBody List<BranchDTO> branchDTOS);

 ResponseEntity<BranchDTO> getDetailOfBranchById(@PathVariable long branchId);

 ResponseEntity<Void> updateBranch(@PathVariable long branchId,
                                        @RequestBody BranchDTO branchDTO);

 ResponseEntity<List<BranchDTO>> getAllBranchInformation(
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(defaultValue = "10") int size,
         @RequestParam(defaultValue = "id, Asc") String[] sort);
}
