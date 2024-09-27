package com.hrmsystem.employeeservice.core.service.branch.impl;

import com.hrmsystem.employeeservice.core.dal.dto.branch.BranchDTO;
import com.hrmsystem.employeeservice.core.dal.dto.common.AddressDTO;
import com.hrmsystem.employeeservice.core.dal.mapping.branch.BranchMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.common.AddressMapper;
import com.hrmsystem.employeeservice.core.service.branch.BranchService;
import com.hrmsystem.employeeservice.core.service.log.AuditService;
import com.hrmsystem.employeeservice.core.service.log.LogService;
import dal.model.branch.Address;
import dal.model.branch.Branch;
import dal.model.organization.Organization;
import dal.repository.branch.BranchRepository;
import dal.repository.common.AddressRepository;
import dal.repository.organization.OrganizationRepository;
import exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private static final String BRANCH = "Branch";
    private static final String USERNAME = "username";
    private static final String CREATE = "Create";
    private static final String UPDATE = "Update";
    private static final String DELETE = "Delete";

    private final BranchMapper branchMapper;
    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;
    private final AuditService auditService;
    private final AddressMapper addressMapper;  // Updated to use AddressMapper
    private final AddressRepository addressRepository;  // Updated to use AddressRepository
    private final LogService logService;
    private static final Logger log = LoggerFactory.getLogger(BranchServiceImpl.class);

    private Branch saveAndLog(Branch branch, String action) {
        Branch savedBranch = branchRepository.save(branch);
        auditService.logAction(USERNAME, BRANCH, action, savedBranch.getId());
        return savedBranch;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createBranch(long organizationId, List<BranchDTO> branchDTOs) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by id: " + organizationId));

        List<Branch> branchList = branchDTOs.stream()
                .map(branchDTO -> {
                    Branch branch = branchMapper.toBranch(branchDTO);
                    setBranchAddress(branch, branchDTO.getBranchAddressDTO());  // Updated to use AddressDTO
                    branch.setOrganization(organization);
                    return branch;
                })
                .collect(Collectors.toList());

        branchRepository.saveAll(branchList);
        branchList.forEach(branch -> auditService.logAction(USERNAME, BRANCH, CREATE, branch.getId()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BranchDTO getDetailOfBranchById(long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found by id: " + branchId));

        BranchDTO branchDTO = branchMapper.toBranchDTO(branch);
        branchDTO.setBranchAddressDTO(addressMapper.toAddressDTO(branch.getBranchAddress()));  // Updated to use AddressDTO
        branchDTO.setOrganizationId(branch.getOrganization().getId());

        return branchDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BranchDTO updateBranch(long branchId, BranchDTO branchDTO) {
        Branch existingBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found by id: " + branchId));

        if (!existingBranch.getOrganization().getId().equals(branchDTO.getOrganizationId())) {
            throw new IllegalArgumentException("Organization ID in the request does not match the one in the database");
        }

        // Update branch and address using mappers
        branchMapper.updateBranch(branchDTO, existingBranch);
        updateBranchAddress(existingBranch, branchDTO.getBranchAddressDTO());  // Updated to use AddressDTO

        Branch updatedBranch = saveAndLog(existingBranch, UPDATE);
        return branchMapper.toBranchDTO(updatedBranch);
    }

    private void setBranchAddress(Branch branch, AddressDTO addressDTO) {
        if (addressDTO != null) {
            Address address = addressMapper.toAddress(addressDTO);  // Use Address entity
            address = addressRepository.save(address);  // Ensure address is persisted
            branch.setBranchAddress(address);
            address.setBranch(branch);  // Ensure bidirectional relationship
        }
    }

    private void updateBranchAddress(Branch branch, AddressDTO addressDTO) {
        if (addressDTO != null) {
            Address address = branch.getBranchAddress();

            if (address == null) {
                // If no existing address, create and associate a new one
                setBranchAddress(branch, addressDTO);
            } else {
                // If address exists, update it using the mapper
                addressMapper.updateAddress(addressDTO, address);  // Use Address entity and AddressMapper
                branch.setBranchAddress(address);
            }
        } else {
            // If no AddressDTO is provided, disassociate the address
            branch.setBranchAddress(null);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<BranchDTO> getAllBranchInformation(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Branch> branches = branchRepository.findAll(pageable);

        if (page > branches.getTotalPages() - 1) {
            logService.log("Requested page exceeds total number of pages. Returning empty list.");
            return Collections.emptyList();
        }

        logService.log("Successfully retrieved all branch information.");
        return branches.stream()
                .map(branchMapper::toBranchDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteBranch(long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found by id: " + branchId));
        branchRepository.delete(branch);
        auditService.logAction(USERNAME, BRANCH, DELETE, branchId);
    }

    @Override
    public Page<BranchDTO> getBranchesByOrganizationId(Long organizationId, Pageable pageable) {
        Page<Branch> branches = branchRepository.findByOrganizationId(organizationId, pageable);
        return branches.map(branchMapper::toBranchDTO);
    }
}
