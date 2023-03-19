package et.hrms.service.impl;


import et.hrms.dal.dto.BranchDTO;
import et.hrms.dal.dto.OrganizationAddressDTO;
import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.mapping.BranchMapper;
import et.hrms.dal.mapping.OrganizationAddressMapper;
import et.hrms.dal.mapping.OrganizationMapper;
import et.hrms.dal.model.Branch;
import et.hrms.dal.model.Organization;
import et.hrms.dal.model.OrganizationAddress;
import et.hrms.dal.repository.BranchRepository;
import et.hrms.dal.repository.OrganizationAddressRepository;
import et.hrms.dal.repository.OrganizationRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.AuditService;
import et.hrms.service.BranchService;
import et.hrms.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {


    private final BranchMapper branchMapper;
    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;
    private final AuditService auditService;
    private final OrganizationAddressMapper organizationAddressMapper;
    private final OrganizationAddressRepository organizationAddressRepository;
    private final LogService logService;
    private final OrganizationMapper organizationMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BranchDTO getBranchById(long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch is not found by this id: " + branchId));
        OrganizationAddressDTO organizationAddressDTO = organizationAddressMapper.toOrganizationAddressDTO(branch.getOrganizationAddress());
        OrganizationDTO organizationDTO = organizationMapper.toOrganizationDTO(branch.getOrganization());
        BranchDTO branchDTO = branchMapper.toBranchDTO(branch);
        branchDTO.setOrganizationAddressDTO(organizationAddressDTO);
        branchDTO.setOrganizationId(organizationDTO.getOrganizationId());

        return branchDTO;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BranchDTO updateBranch(long branchId, BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch with ID " + branchId + " not found"));

        branch.setBranchCode(branchDTO.getBranchCode());
        branch.setBranchName(branchDTO.getBranchName());
        Long addressId = branch.getOrganizationAddress().getId();

        OrganizationAddress existingOrganizationAddress = organizationAddressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("OrganizationAddress not found by this id: " + addressId));

        branch.setOrganizationAddress(existingOrganizationAddress);
        existingOrganizationAddress = organizationAddressMapper.toOrganizationAddress(branchDTO.getOrganizationAddressDTO());
        existingOrganizationAddress.setId(addressId);
        existingOrganizationAddress.setBranch(branch);

        branch.setOrganizationAddress(existingOrganizationAddress);

        Organization organization = organizationRepository.findById(branchDTO.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization with ID " + branchDTO.getOrganizationId() + " not found"));

        branch.setOrganization(organization);

        branch = branchRepository.save(branch);

        auditService.logAction("username", "Branch", "Update", branch.getId());
        return branchMapper.toBranchDTO(branch);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<BranchDTO> getAllBranchInformation(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Branch> branches = branchRepository.findAll(pageable);
        if (page > branches.getTotalPages()) {
            throw new EntityNotFoundException("pages is not found");
        }
        logService.log("Successfully retrieved all branch information.");
        return branches.stream().map(branchMapper::toBranchDTO).toList();
    }
}
