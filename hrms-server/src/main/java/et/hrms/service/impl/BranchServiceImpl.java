package et.hrms.service.impl;


import et.hrms.dal.dto.BranchDTO;
import et.hrms.dal.mapping.BranchMapper;
import et.hrms.dal.mapping.OrganizationAddressMapper;
import et.hrms.dal.mapping.OrganizationMapper;
import et.hrms.dal.model.Branch;
import et.hrms.dal.model.Organization;
import et.hrms.dal.model.OrganizationAddress;
import et.hrms.dal.repository.BranchRepository;
import et.hrms.dal.repository.OrganizationRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.AuditService;
import et.hrms.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {


    private final BranchMapper branchMapper;
    private final OrganizationMapper organizationMapper;
    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;
    private final AuditService auditService;

    private final OrganizationAddressMapper organizationAddressMapper;



    @Override
    public List<BranchDTO> createBranch(long organizationId, BranchDTO branchDTO) {

        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new EntityNotFoundException("Organization is not found by id:" + organizationId));

        Set<Branch> branches = new LinkedHashSet<>();
        OrganizationAddress organizationAddress = organizationAddressMapper.toOrganizationAddress(branchDTO.getOrganizationAddressDTO());
        Branch branch = branchMapper.toBranch(branchDTO);
        branch.setOrganization(organization);
        branch.setOrganizationAddress(organizationAddress);
        branches.add(branch);
        auditService.logAction("username", "Branch", "Create", branch.getId());

        return branchMapper.toBranchDTOs(branchRepository.saveAll(branches));
    }


    @Override
    public BranchDTO getBranchById(long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch is not found by this id: " + branchId));

        return branchMapper.toBranchDTO(branch);
    }


    @Override
    public BranchDTO updateBranch(long branchId, BranchDTO branchDTO) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch information is not found by this id: " + branchId));
        long orgId = branch.getOrganization().getId();
//        Organization organization = organizationRepository.findById(branch.getOrganization().getId())
//                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Organization information is not found by this id: {0}", orgId)));
        if(branch.getId() != null){
            Organization org = new Organization();
            branch = branchMapper.toBranch(branchDTO);
            org.setId(branchDTO.getOrganizationId());
            branch.setOrganization(org);

        }
        auditService.logAction("username", "Branch", "Update", branch.getId());
        return branchMapper.toBranchDTO(branchRepository.save(branch));
    }

    @Override
    public List<BranchDTO> getAllBranchInformation(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Branch> branches = branchRepository.findAll(pageable);
        if (page > branches.getTotalPages()) {
            throw new EntityNotFoundException("pages is not found");
        }
        return branches.stream().map(branchMapper::toBranchDTO).toList();
    }
}
