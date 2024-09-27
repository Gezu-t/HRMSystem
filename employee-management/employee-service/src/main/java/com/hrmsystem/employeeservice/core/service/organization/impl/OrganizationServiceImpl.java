package com.hrmsystem.employeeservice.core.service.organization.impl;

import com.hrmsystem.employeeservice.core.dal.dto.branch.BranchDTO;
import com.hrmsystem.employeeservice.core.dal.dto.common.AddressDTO;
import com.hrmsystem.employeeservice.core.dal.dto.department.DepartmentDTO;
import com.hrmsystem.employeeservice.core.dal.dto.employee.EmployeeDTO;
import com.hrmsystem.employeeservice.core.dal.dto.organization.OrganizationDTO;
import com.hrmsystem.employeeservice.core.dal.dto.organization.OwnersDTO;
import com.hrmsystem.employeeservice.core.dal.mapping.branch.BranchMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.common.AddressMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.department.DepartmentMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.employee.EmployeeMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.organization.OrganizationMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.organization.OwnersMapper;
import com.hrmsystem.employeeservice.core.service.log.AuditService;
import com.hrmsystem.employeeservice.core.service.log.LogService;
import com.hrmsystem.employeeservice.core.service.organization.OrganizationService;
import dal.model.branch.Branch;
import dal.model.department.Department;
import dal.model.employee.Employee;
import dal.model.organization.Organization;
import dal.model.organization.Owners;
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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
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
        organization.setUpdatedAt(LocalDateTime.now());

        // Set Address
        setOrganizationAddress(organization, organizationDTO.getOrganizationAddressDTO());

        // Set Other Entities
        setOwners(organization, organizationDTO.getOwners());
        setBranches(organization, organizationDTO.getBranches());
        setDepartments(organization, organizationDTO.getDepartments());
        setEmployees(organization, organizationDTO.getEmployees());

        Organization savedOrganization = organizationRepository.save(organization);
        log.info("Saved organization object: {}", savedOrganization);
        auditService.logAction("username", "Organization", "Create", savedOrganization.getId());
    }

    private void setOrganizationAddress(Organization organization, AddressDTO addressDTO) {
        if (addressDTO != null) {
            organization.setOrganizationAddress(addressMapper.toAddress(addressDTO));
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

    // Fix: Consistent use of DTO-to-Entity mapping in the updateDepartments method
    private void setDepartments(Organization organization, List<DepartmentDTO> departmentDTOs) {
        if (departmentDTOs != null) {
            List<Department> departments = departmentDTOs.stream()
                    .map(departmentDTO -> {
                        Department department = departmentDTO.getId() != null
                                ? organization.getDepartments().stream()
                                .filter(d -> d.getId().equals(departmentDTO.getId()))
                                .findFirst()
                                .orElse(new Department())
                                : new Department();

                        departmentMapper.updateDepartment(departmentDTO, department); // Correct order
                        department.setOrganization(organization); // Maintain relationship
                        return department;
                    })
                    .collect(Collectors.toList());
            organization.setDepartments(departments);
        }
    }

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

    @Override
    @Transactional
    public OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO) {
        Organization existingOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by this id: " + id));

        updateExistingOrganization(existingOrganization, organizationDTO);

        existingOrganization.setUpdatedAt(LocalDateTime.now());
        Organization updatedOrganization = organizationRepository.save(existingOrganization);
        auditService.logAction("username", "Organization", "Update", updatedOrganization.getId());
        return organizationMapper.toOrganizationDTO(updatedOrganization);
    }

    private void updateExistingOrganization(Organization existingOrganization, OrganizationDTO organizationDTO) {
        existingOrganization.setOrganizationName(organizationDTO.getOrganizationName());
        existingOrganization.setOrganizationCode(organizationDTO.getOrganizationCode());
        existingOrganization.setEstablishmentDate(organizationDTO.getEstablishmentDate());

        // Update Address
        updateOrganizationAddress(existingOrganization, organizationDTO.getOrganizationAddressDTO());

        // Update other entities
        // Update other entities using set methods
        setOwners(existingOrganization, organizationDTO.getOwners());
        setBranches(existingOrganization, organizationDTO.getBranches());
        setDepartments(existingOrganization, organizationDTO.getDepartments());
        setEmployees(existingOrganization, organizationDTO.getEmployees());
    }

    private void updateOrganizationAddress(Organization organization, AddressDTO addressDTO) {
        if (addressDTO != null) {
            if (organization.getOrganizationAddress() == null) {
                organization.setOrganizationAddress(addressMapper.toAddress(addressDTO));
            } else {
                addressMapper.updateAddress(addressDTO, organization.getOrganizationAddress()); // Correct order
            }
        } else {
            organization.setOrganizationAddress(null);
        }
    }

    private void updateOwners(Organization organization, List<OwnersDTO> ownersDTOs) {
        if (ownersDTOs == null) {
            organization.getOwners().clear();
        } else {
            List<Owners> updatedOwners = ownersDTOs.stream()
                    .map(ownerDTO -> {
                        Owners owner = ownerDTO.getId() != null
                                ? organization.getOwners().stream()
                                .filter(o -> o.getId().equals(ownerDTO.getId()))
                                .findFirst()
                                .orElse(new Owners())
                                : new Owners();

                        ownersMapper.updateOwners(ownerDTO, owner); // Correct order
                        owner.setOrganization(organization);
                        return owner;
                    })
                    .collect(Collectors.toList());
            organization.getOwners().clear();
            organization.getOwners().addAll(updatedOwners);
        }
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
