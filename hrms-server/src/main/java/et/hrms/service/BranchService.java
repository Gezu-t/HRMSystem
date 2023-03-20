package et.hrms.service;

import et.hrms.dal.dto.BranchDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BranchService {

    List<BranchDTO> createBranch(long organizationId, BranchDTO branchDTOs);

    BranchDTO getDetailOfBranchById(long branchId);

    BranchDTO updateBranch(long branchId, BranchDTO branchDTO);

    List<BranchDTO> getAllBranchInformation(int page, int size);
}
