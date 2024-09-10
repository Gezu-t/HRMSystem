package com.hrmsystem.employeeprofileservice.service.organization.impl;

import com.hrmsystem.employeeprofileservice.dal.dto.department.OrganizationAddressDTO;
import com.hrmsystem.employeeprofileservice.dal.dto.department.OrganizationDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.organization.OrganizationAddressMapper;
import com.hrmsystem.employeeprofileservice.dal.mapping.organization.OrganizationMapper;
import com.hrmsystem.employeeprofileservice.service.log.AuditService;
import com.hrmsystem.employeeprofileservice.service.log.LogService;
import com.hrmsystem.employeeprofileservice.service.organization.OrganizationService;

import dal.model.organization.Organization;
import dal.model.organization.OrganizationAddress;
import dal.repository.organization.OrganizationAddressRepository;
import dal.repository.organization.OrganizationRepository;
import exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final OrganizationAddressMapper organizationAddressMapper;
    private final OrganizationAddressRepository organizationAddressRepository;
    private final AuditService auditService;
    private final LogService logService;

    @Override
    @Transactional
    public void createOrganization(OrganizationDTO organizationDTO) {
        Organization organization = organizationMapper.toOrganization(organizationDTO);
        OrganizationAddress organizationAddress = organizationAddressMapper.toOrganizationAddress(organizationDTO.getOrganizationAddressDTO());

        organizationAddress.setOrganization(organization);
        organization.setOrganizationAddress(organizationAddress);

        Organization savedOrganization = organizationRepository.save(organization);
        log.info("Saved organization object: {}", savedOrganization);
        auditService.logAction("username", "Organization", "Create", savedOrganization.getId());
    }

    @Override
    public OrganizationDTO getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by this id: " + id));
        return organizationMapper.toOrganizationDTO(organization);
    }

    @Override
    @Transactional
    public OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO) {
        Organization existingOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by this id: " + id));

        OrganizationAddress existingOrganizationAddress = existingOrganization.getOrganizationAddress();
        if (existingOrganizationAddress == null) {
            existingOrganizationAddress = new OrganizationAddress();
            existingOrganization.setOrganizationAddress(existingOrganizationAddress);
            existingOrganizationAddress.setOrganization(existingOrganization);
        }

        updateExistingOrganization(existingOrganization, organizationDTO);
        updateExistingOrganizationAddress(existingOrganizationAddress, organizationDTO.getOrganizationAddressDTO());

        auditService.logAction("username", "Organization", "Update", existingOrganization.getId());
        return organizationMapper.toOrganizationDTO(organizationRepository.save(existingOrganization));
    }

    private void updateExistingOrganization(Organization existingOrganization, OrganizationDTO organizationDTO) {
        existingOrganization.setOrganizationName(organizationDTO.getOrganizationName());
        existingOrganization.setEstablishmentDate(organizationDTO.getEstablishmentDate());
        existingOrganization.setOwnerName(organizationDTO.getOwnerName());
        // Update other fields as necessary
    }

    private void updateExistingOrganizationAddress(OrganizationAddress existingOrganizationAddress, OrganizationAddressDTO organizationAddressDTO) {
        existingOrganizationAddress.setTelNumberHome(organizationAddressDTO.getTelNumberHome());
        existingOrganizationAddress.setTelNumberOffice(organizationAddressDTO.getTelNumberOffice());
        existingOrganizationAddress.setMobile(organizationAddressDTO.getMobile());
        existingOrganizationAddress.setHouseNumber(organizationAddressDTO.getHouseNumber());
        existingOrganizationAddress.setStreet(organizationAddressDTO.getStreet());
        existingOrganizationAddress.setStreetNumber(organizationAddressDTO.getStreetNumber());
        existingOrganizationAddress.setBuilding(organizationAddressDTO.getBuilding());
        existingOrganizationAddress.setFloor(organizationAddressDTO.getFloor());
        existingOrganizationAddress.setFlat(organizationAddressDTO.getFlat());
        existingOrganizationAddress.setRegion(organizationAddressDTO.getRegion());
        existingOrganizationAddress.setProvince(organizationAddressDTO.getProvince());
        existingOrganizationAddress.setCity(organizationAddressDTO.getCity());
        existingOrganizationAddress.setPostalCode(organizationAddressDTO.getPostalCode());
        existingOrganizationAddress.setCountry(organizationAddressDTO.getCountry());
    }

    @Override
    public List<OrganizationDTO> getAllOrganization(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Organization> organizations = organizationRepository.findAll(pageable);
        // If requested page exceeds total pages, return an empty list or handle appropriately
        if (page > organizations.getTotalPages() - 1) {
            logService.log("Requested page exceeds total number of pages. Returning empty list.");
            return Collections.emptyList();
        }

        logService.log("Successfully retrieved all organization information.");

        return organizations.stream()
                .map(organizationMapper::toOrganizationDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteOrganization(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by this id: " + id));
        organizationRepository.delete(organization);
        auditService.logAction("username", "Organization", "Delete", id);
        log.info("Deleted organization with id: {}", id);
    }

    @Override
    public List<OrganizationDTO> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream()
                .map(organizationMapper::toOrganizationDTO)
                .collect(Collectors.toList());
    }
}
