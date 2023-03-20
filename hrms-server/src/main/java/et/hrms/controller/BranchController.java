package et.hrms.controller;

import et.hrms.dal.dto.BranchDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface BranchController {


    @PostMapping("/{organizationId}")
    ResponseEntity<List<BranchDTO>> createBranch(@PathVariable long organizationId, @RequestBody BranchDTO branchDTO);

    @GetMapping("/{branchId}")
    ResponseEntity<BranchDTO> getBranchById(@PathVariable long branchId);

    @PutMapping("/{branchId}")
    ResponseEntity<BranchDTO> updateBranch(@PathVariable long branchId, @RequestBody BranchDTO branchDTO);

    @GetMapping
    ResponseEntity<List<BranchDTO>> getAllBranchInformation(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size);
}
