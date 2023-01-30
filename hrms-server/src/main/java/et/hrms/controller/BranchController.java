package et.hrms.controller;

import et.hrms.dal.dto.BranchDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface BranchController {

    List<BranchDTO> createBranch(@Valid @RequestBody BranchDTO branchDTO,
                                 @PathVariable(name = "organizationId") long organizationId);

    BranchDTO getBranchById(@PathVariable long id);

    BranchDTO updateBranch(@PathVariable long branchId, @RequestBody BranchDTO branchDTO);

    public ResponseEntity<List<BranchDTO>> getAllBranchInformation(@RequestParam("page") int page,
                                                                    @RequestParam("size") int size,
                                                                    UriComponentsBuilder uriBuilder,
                                                                    HttpServletResponse response);
}
