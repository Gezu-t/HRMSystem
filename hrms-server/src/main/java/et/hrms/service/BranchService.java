package et.hrms.service;

import et.hrms.dal.dto.BranchDTO;

import java.util.List;

public interface BranchService {

    void createBranch(long organizationId, List<BranchDTO> branchDTOS);

    BranchDTO getDetailOfBranchById(long branchId);

    BranchDTO updateBranch(long branchId, BranchDTO branchDTO);

    List<BranchDTO> getAllBranchInformation(int page, int size);
}
