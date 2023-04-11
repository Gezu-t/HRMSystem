package et.hrms.controller.impl;


import et.hrms.controller.BranchController;
import et.hrms.dal.dto.BranchDTO;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Void> createBranch(@PathVariable long organizationId,
                                             @RequestBody List<BranchDTO> branchDTOS) {
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
    public ResponseEntity<Void> updateBranch(@PathVariable long branchId,
                                                  @RequestBody BranchDTO branchDTO) {
        branchService.updateBranch(branchId, branchDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranchInformation(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id, Asc") String[] sort) {


        Sort sortOrder = Sort.by(sort[0]);
        if (sort.length > 1) {
            sortOrder = sort[1].equalsIgnoreCase("desc") ? sortOrder.descending() : sortOrder.ascending();
        }

        try {
            List<BranchDTO> branchDTOs = branchService.getAllBranchInformation(page, size, sortOrder);
            return new ResponseEntity<>(branchDTOs, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
