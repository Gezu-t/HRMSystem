package com.hrmsystem.employeeservice.core.service.organization.impl;

import com.hrmsystem.employeeservice.core.dal.mapping.branch.BranchMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.common.AddressMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.department.DepartmentMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.employee.EmployeeMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.organization.OrganizationMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.organization.OwnersMapper;
import com.hrmsystem.employeeservice.core.exceptions.EntityNotFoundException;
import com.hrmsystem.employeeservice.core.service.log.AuditService;
import com.hrmsystem.employeeservice.core.service.log.LogService;
import com.hrmsystem.employeeservice.core.service.organization.OrganizationService;
import dal.dto.branch.BranchDTO;
import dal.dto.common.AddressDTO;
import dal.dto.employee.EmployeeDTO;
import dal.dto.organization.OrganizationDTO;
import dal.dto.organization.OwnersDTO;
import dal.model.branch.Address;
import dal.model.branch.Branch;
import dal.model.employee.Employee;
import dal.model.organization.Organization;
import dal.model.organization.Owners;
import dal.repository.organization.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final AddressMapper addressMapper;
    private final OwnersMapper ownersMapper;
    private final BranchMapper branchMapper;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;
    private final AuditService auditService;
    private final LogService logService;

    @Override
    @Transactional
    public void createOrganization(OrganizationDTO organizationDTO) {
        Organization organization = organizationMapper.toOrganization(organizationDTO);
        organization.setCreatedAt(LocalDateTime.now());
        // Set Address
        setAddresses(organization, organizationDTO.getAddresses());
        // Set Other Entities
        setOwners(organization, organizationDTO.getOwners());

        Organization savedOrganization = organizationRepository.save(organization);
        log.info("Saved organization object: {}", savedOrganization);
        auditService.logAction("username", "Organization", "Create", savedOrganization.getId());
    }


    private void setAddresses(Organization organization, List<AddressDTO> addressDTOs) {

        log.debug("Setting addresses for organization: {}", organization.getId());
        log.debug("Received addressDTOs: {}", addressDTOs);
        if (addressDTOs != null) {
            if (organization.getAddresses() == null) {
                organization.setAddresses(new ArrayList<>());
            }

            List<Address> addresses = addressDTOs.stream()
                    .filter(Objects::nonNull)
                    .map(addressDTO -> {
                        Address address = addressDTO.getId() != null
                                ? organization.getAddresses().stream()
                                .filter(a -> a.getId().equals(addressDTO.getId()))
                                .findFirst().orElse(new Address())
                                : new Address();

                        addressMapper.updateAddress(addressDTO, address);
                        address.setOrganization(organization);
                        return address;
                    }).toList();

            organization.getAddresses().clear();
            organization.getAddresses().addAll(addresses);
            log.debug("Mapped addresses: {}", addresses);
        } else {
            log.debug("AddressDTOs is null, setting organization address to null");
            organization.setAddresses(null);
        }
    }


    // Fix: Consistent use of DTO-to-Entity mapping in the updateOwners method
    private void setOwners(Organization organization, List<OwnersDTO> ownersDTOs) {
        if (ownersDTOs != null) {
            List<Owners> owners = ownersDTOs.stream()
                    .map(ownersDTO -> {
                        Owners owner = ownersDTO.getId() != null
                                ? organization.getOwners().stream()
                                .filter(o -> o.getId().equals(ownersDTO.getId()))
                                .findFirst()
                                .orElse(new Owners())
                                : new Owners();

                        ownersMapper.updateOwners(ownersDTO, owner); // Correct order
                        owner.setOrganization(organization); // Maintain relationship
                        return owner;
                    })
                    .collect(Collectors.toList());
            organization.setOwners(owners);
        }
    }

    // Fix: Consistent use of DTO-to-Entity mapping in the updateBranches method
    private void setBranches(Organization organization, List<BranchDTO> branchDTOs) {
        if (branchDTOs != null) {
            List<Branch> branches = branchDTOs.stream()
                    .map(branchDTO -> {
                        Branch branch = branchDTO.getId() != null
                                ? organization.getBranches().stream()
                                .filter(b -> b.getId().equals(branchDTO.getId()))
                                .findFirst()
                                .orElse(new Branch())
                                : new Branch();

                        branchMapper.updateBranch(branchDTO, branch); // Correct order
                        branch.setOrganization(organization); // Maintain relationship
                        return branch;
                    })
                    .collect(Collectors.toList());
            organization.setBranches(branches);
        }
    }

//    // Fix: Consistent use of DTO-to-Entity mapping in the updateDepartments method
//    private void setDepartments(Organization organization, List<DepartmentDTO> departmentDTOs) {
//        if (departmentDTOs != null) {
//            List<Department> departments = departmentDTOs.stream()
//                    .map(departmentDTO -> {
//                        Department department = departmentDTO.getId() != null
//                                ? organization.getDepartments().stream()
//                                .filter(d -> d.getId().equals(departmentDTO.getId()))
//                                .findFirst()
//                                .orElse(new Department())
//                                : new Department();
//
//                        departmentMapper.updateDepartment(departmentDTO, department); // Correct order
//                        department.setOrganization(organization); // Maintain relationship
//                        return department;
//                    })
//                    .collect(Collectors.toList());
//            organization.setDepartments(departments);
//        }
//    }

    // Fix: Consistent use of DTO-to-Entity mapping in the updateEmployees method
    private void setEmployees(Organization organization, List<EmployeeDTO> employeeDTOs) {
        if (employeeDTOs != null) {
            List<Employee> employees = employeeDTOs.stream()
                    .map(employeeDTO -> {
                        Employee employee = employeeDTO.getId() != null
                                ? organization.getEmployees().stream()
                                .filter(e -> e.getId().equals(employeeDTO.getId()))
                                .findFirst()
                                .orElse(new Employee())
                                : new Employee();

                        employeeMapper.updateEmployee(employeeDTO, employee); // Correct order
                        employee.setOrganization(organization); // Maintain relationship
                        return employee;
                    })
                    .collect(Collectors.toList());
            organization.setEmployees(employees);
        }
    }

    @Override
    public OrganizationDTO getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by this id: " + id));
        return organizationMapper.toOrganizationDTO(organization);
    }
//
//    @Override
//    @Transactional
//    public OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO) {
//        Organization existingOrganization = organizationRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Organization not found by this id: " + id));
//
//        updateExistingOrganization(existingOrganization, organizationDTO);
//
//        existingOrganization.setUpdatedAt(LocalDateTime.now());
//        Organization updatedOrganization = organizationRepository.save(existingOrganization);
//        auditService.logAction("username", "Organization", "Update", updatedOrganization.getId());
//        return organizationMapper.toOrganizationDTO(updatedOrganization);
//    }

//    private void updateExistingOrganization(Organization existingOrganization, OrganizationDTO organizationDTO) {
//        existingOrganization.setOrganizationName(organizationDTO.getOrganizationName());
//        existingOrganization.setOrganizationCode(organizationDTO.getOrganizationCode());
//        existingOrganization.setEstablishmentDate(organizationDTO.getEstablishmentDate());
//
//        // Update Address
//        setAddresses(existingOrganization, organizationDTO.getAddresses());
//
//        // Update other entities
//        // Update other entities using set methods
//        setOwners(existingOrganization, organizationDTO.getOwners());
//
////        setBranches(existingOrganization, organizationDTO.getBranches());
////        setDepartments(existingOrganization, organizationDTO.getDepartments());
////        setEmployees(existingOrganization, organizationDTO.getEmployees());
//    }

    @Override
    @Transactional
    public OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO) {
        Organization existingOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by this id: " + id));

        // Update simple fields
        existingOrganization.setOrganizationName(organizationDTO.getOrganizationName());
        existingOrganization.setOrganizationCode(organizationDTO.getOrganizationCode());
        existingOrganization.setEstablishmentDate(organizationDTO.getEstablishmentDate());

        // Update owners
        updateOwners(existingOrganization, organizationDTO.getOwners());

        // Update addresses
        updateAddresses(existingOrganization, organizationDTO.getAddresses());

        existingOrganization.setUpdatedAt(LocalDateTime.now());
        Organization updatedOrganization = organizationRepository.save(existingOrganization);
        return organizationMapper.toOrganizationDTO(updatedOrganization);
    }

    private void updateOwners(Organization organization, List<OwnersDTO> ownersDTOs) {
        // Clear existing owners if the new list is empty
        if (ownersDTOs == null || ownersDTOs.isEmpty()) {
            organization.getOwners().clear();
            return;
        }

        // Create a map of existing owners by ID
        Map<Long, Owners> existingOwnersMap = organization.getOwners().stream()
                .collect(Collectors.toMap(Owners::getId, Function.identity()));

        List<Owners> updatedOwners = new ArrayList<>();

        for (OwnersDTO ownerDTO : ownersDTOs) {
            Owners owner;
            if (ownerDTO.getId() != null && existingOwnersMap.containsKey(ownerDTO.getId())) {
                // Update existing owner
                owner = existingOwnersMap.get(ownerDTO.getId());
                existingOwnersMap.remove(ownerDTO.getId());
            } else {
                // Create new owner
                owner = new Owners();
                owner.setOrganization(organization);
            }
            // Update owner properties
            ownersMapper.updateOwners(ownerDTO, owner);
            updatedOwners.add(owner);
        }

        // Remove owners that are no longer in the DTO
        organization.getOwners().removeAll(existingOwnersMap.values());

        // Set the updated list of owners
        organization.getOwners().clear();
        organization.getOwners().addAll(updatedOwners);
    }

    private void updateAddresses(Organization organization, List<AddressDTO> addressDTOs) {
        // Clear existing addresses if the new list is empty
        if (addressDTOs == null || addressDTOs.isEmpty()) {
            organization.getAddresses().clear();
            return;
        }

        // Create a map of existing addresses by ID
        Map<Long, Address> existingAddressesMap = organization.getAddresses().stream()
                .collect(Collectors.toMap(Address::getId, Function.identity()));

        List<Address> updatedAddresses = new ArrayList<>();

        for (AddressDTO addressDTO : addressDTOs) {
            Address address;
            if (addressDTO.getId() != null && existingAddressesMap.containsKey(addressDTO.getId())) {
                // Update existing address
                address = existingAddressesMap.get(addressDTO.getId());
                existingAddressesMap.remove(addressDTO.getId());
            } else {
                // Create new address
                address = new Address();
                address.setOrganization(organization);
            }
            // Update address properties
            addressMapper.updateAddress(addressDTO, address);
            updatedAddresses.add(address);
        }

        // Remove addresses that are no longer in the DTO
        organization.getAddresses().removeAll(existingAddressesMap.values());

        // Set the updated list of addresses
        organization.getAddresses().clear();
        organization.getAddresses().addAll(updatedAddresses);
    }

    @Override
    public List<OrganizationDTO> getAllOrganization(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Organization> organizations = organizationRepository.findAll(pageable);
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
