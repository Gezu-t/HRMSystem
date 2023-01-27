package et.hrms.controller.impl;


import et.hrms.controller.BranchController;
import et.hrms.dal.dto.BranchDTO;
import et.hrms.service.BranchService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchControllerImpl implements BranchController {


    private final BranchService branchService;

    private final ApplicationEventPublisher applicationEventPublisher;


    @Override
    @PostMapping(value = "/add/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO,
                                        @PathVariable long organizationId) {
        return branchService.createBranch(organizationId, branchDTO);
    }


    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public BranchDTO getBranchById(@Valid @PathVariable long id) {
        return branchService.getBranchById(id);
    }


    @Override
    @PutMapping(path = "/{branchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BranchDTO updateBranch(@PathVariable long branchId, @RequestBody BranchDTO branchDTO) {
        return branchService.updateBranch(branchId, branchDTO);
    }

    @Override
    @GetMapping(params = { "page", "size" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BranchDTO> getAllBranchInformation(@RequestParam("page") int page,
                                                   @RequestParam("size") int size,
                                                   UriComponentsBuilder uriBuilder,
                                                   HttpServletResponse response){

        return branchService.getAllBranchInformation(page, size);
    }

}
