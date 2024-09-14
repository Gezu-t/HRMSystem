package com.hrmsystem.employeeservice.core.service.branch.impl;

import com.hrmsystem.employeeservice.core.dal.dto.department.BranchAddressDTO;
import com.hrmsystem.employeeservice.core.dal.dto.department.BranchDTO;
import com.hrmsystem.employeeservice.core.dal.mapping.branch.BranchAddressMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.branch.BranchMapper;
import com.hrmsystem.employeeservice.core.service.branch.BranchService;
import com.hrmsystem.employeeservice.core.service.log.AuditService;
import com.hrmsystem.employeeservice.core.service.log.LogService;
import dal.model.branch.Branch;
import dal.model.branch.BranchAddress;
import dal.model.organization.Organization;
import dal.repository.branch.BranchAddressRepository;
import dal.repository.branch.BranchRepository;
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

import java.util.ArrayList;
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
    private final BranchAddressMapper branchAddressMapper;
    private final BranchAddressRepository branchAddressRepository;
    private final LogService logService;
    private final static Logger log = LoggerFactory.getLogger(BranchServiceImpl.class);

    private Branch saveAndLog(Branch branch, String action) {
        Branch savedBranch = branchRepository.save(branch);
        auditService.logAction(USERNAME, BRANCH, action, savedBranch.getId());
        return savedBranch;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createBranch(long organizationId, List<BranchDTO> branchDTOS) {
        try {
            Organization organization = organizationRepository.findById(organizationId)
                    .orElseThrow(() -> new EntityNotFoundException("Organization not found by id: " + organizationId));
            List<Branch> branchList = new ArrayList<>();

            for (BranchDTO branchDTO : branchDTOS) {
                Branch branch = branchMapper.toBranch(branchDTO);
                BranchAddress branchAddress = branchAddressMapper.toBranchAddress(branchDTO.getBranchAddressDTO());

                // Persist the branchAddress to ensure it's managed
                branchAddress = branchAddressRepository.save(branchAddress);

                branch.setOrganization(organization);
                branch.setBranchAddress(branchAddress);
                branchAddress.setBranch(branch);  // Ensure bidirectional relationship

                branchList.add(branch);
            }

            branchRepository.saveAll(branchList);
            branchList.forEach(branch -> auditService.logAction(USERNAME, BRANCH, CREATE, branch.getId()));
        } catch (Exception e) {
            log.error("Error creating branch: ", e);
            throw e;
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BranchDTO getDetailOfBranchById(long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found by id: " + branchId));
        BranchDTO branchDTO = branchMapper.toBranchDTO(branch);
        BranchAddressDTO branchAddressDTO = branchAddressMapper.toBranchAddressDTO(branch.getBranchAddress());
        branchDTO.setBranchAddressDTO(branchAddressDTO);
        branchDTO.setOrganizationId(branch.getOrganization().getId());
        return branchDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BranchDTO updateBranch(long branchId, BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found by id: " + branchId));

        if (!branch.getOrganization().getId().equals(branchDTO.getOrganizationId())) {
            throw new IllegalArgumentException("Organization ID in the request does not match the one in the database");
        }

        branchMapper.updateBranchFromDTO(branchDTO, branch);
        updateBranchAddress(branch, branchDTO.getBranchAddressDTO());
        Branch updatedBranch = saveAndLog(branch, UPDATE);
        return branchMapper.toBranchDTO(updatedBranch);
    }

    private void updateBranchAddress(Branch branch, BranchAddressDTO branchAddressDTO) {
        Long addressId = branch.getBranchAddress().getId();
        BranchAddress branchAddress = branchAddressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("BranchAddress not found by id: " + addressId));
        branchAddressMapper.updateBranchAddressFromDto(branchAddressDTO, branchAddress);
        branch.setBranchAddress(branchAddress);
    }


//    private void updateOrganizationAddress(Branch branch, OrganizationAddressDTO organizationAddressDTO) {
//        Long addressId = branch.getOrganizationAddress().getId();
//        OrganizationAddress organizationAddress = organizationAddressRepository.findById(addressId)
//                .orElseThrow(() -> new EntityNotFoundException("OrganizationAddress not found by id: " + addressId));
//        organizationAddressMapper.updateOrganizationAddressFromDto(organizationAddressDTO, organizationAddress);
//        branch.setOrganizationAddress(organizationAddress);
//    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<BranchDTO> getAllBranchInformation(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Branch> branches = branchRepository.findAll(pageable);

        // If requested page exceeds total pages, return an empty list or handle appropriately
        if (page > branches.getTotalPages() - 1) {
            logService.log("Requested page exceeds total number of pages. Returning empty list.");
            return Collections.emptyList();
        }

        logService.log("Successfully retrieved all branch information.");
        return branches.stream().map(branchMapper::toBranchDTO).collect(Collectors.toList());
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
