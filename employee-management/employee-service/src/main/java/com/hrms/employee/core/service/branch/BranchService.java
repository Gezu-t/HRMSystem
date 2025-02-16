package com.hrms.employee.core.service.branch;

import com.hrms.employee.core.dal.dto.branch.BranchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BranchService {

    void createBranch(long organizationId, BranchDTO branchDTO);
    BranchDTO getDetailOfBranchById(long branchId);
    BranchDTO updateBranch(long branchId, BranchDTO branchDTO);
    List<BranchDTO> getAllBranchInformation(int page, int size, Sort sort);
    void deleteBranch(long branchId);
    Page<BranchDTO> getBranchesByOrganizationId(Long organizationId, Pageable pageable);
}
