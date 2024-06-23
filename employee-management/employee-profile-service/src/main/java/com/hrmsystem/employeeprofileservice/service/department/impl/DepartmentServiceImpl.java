package com.hrmsystem.employeeprofileservice.service.department.impl;

import com.hrmsystem.employeeprofileservice.dal.dto.department.DepartmentUnderBranchDTO;
import com.hrmsystem.employeeprofileservice.dal.dto.department.DepartmentUnderOrganizationDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.department.DepartmentMapper;
import com.hrmsystem.employeeprofileservice.dal.mapping.department.DepartmentUnderBranchMapper;
import com.hrmsystem.employeeprofileservice.dal.mapping.department.DepartmentUnderOrganizationMapper;
import com.hrmsystem.employeeprofileservice.service.department.DepartmentService;
import com.hrmsystem.employeeprofileservice.service.log.AuditService;
import com.hrmsystem.employeeprofileservice.service.log.LogService;
import com.hrmsystem.employeeservice.core.dal.model.branch.Branch;
import com.hrmsystem.employeeservice.core.dal.model.department.DepartmentUnderBranch;
import com.hrmsystem.employeeservice.core.dal.model.department.DepartmentUnderOrganization;
import com.hrmsystem.employeeservice.core.dal.model.organization.Organization;
import com.hrmsystem.employeeservice.core.dal.repository.branch.BranchRepository;
import com.hrmsystem.employeeservice.core.dal.repository.department.DepartmentUnderBranchRepository;
import com.hrmsystem.employeeservice.core.dal.repository.department.DepartmentUnderOrganizationRepository;
import com.hrmsystem.employeeservice.core.dal.repository.organization.OrganizationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final String DEPARTMENT = "Department";
    private static final String USERNAME = "username";
    private static final String CREATE = "Create";
    private static final String UPDATE = "Update";

    private final DepartmentUnderBranchRepository departmentUnderBranchRepository;
    private final DepartmentUnderOrganizationRepository departmentUnderOrganizationRepository;
    private final DepartmentMapper departmentMapper;
    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;
    private final DepartmentUnderBranchMapper departmentUnderBranchMapper;
    private final DepartmentUnderOrganizationMapper underOrganizationMapper;
    private final AuditService auditService;
    private final LogService logService;

    private <T> T saveAndLog(T department, String action) {
        if (department instanceof DepartmentUnderBranch) {
            DepartmentUnderBranch savedDepartment = departmentUnderBranchRepository.save((DepartmentUnderBranch) department);
            auditService.logAction(USERNAME, DEPARTMENT, action, savedDepartment.getId());
            return (T) savedDepartment;
        } else {
            DepartmentUnderOrganization savedDepartment = departmentUnderOrganizationRepository.save((DepartmentUnderOrganization) department);
            auditService.logAction(USERNAME, DEPARTMENT, action, savedDepartment.getId());
            return (T) savedDepartment;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createDepartmentByBranchId(long branchId, List<DepartmentUnderBranchDTO> departmentUnderBranchDTOS) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found by id: " + branchId));

        List<DepartmentUnderBranch> departments = departmentUnderBranchDTOS.stream()
                .map(departmentUnderBranchMapper::toDepartmentUnderBranch)
                .peek(department -> department.setBranch(branch))
                .collect(Collectors.toList());

        departmentUnderBranchRepository.saveAll(departments);
        departments.forEach(department -> auditService.logAction(USERNAME, DEPARTMENT, CREATE, department.getId()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createDepartmentByOrganizationId(Long organizationId, List<DepartmentUnderOrganizationDTO> underOrganizationDTOS) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by id: " + organizationId));

        List<DepartmentUnderOrganization> departments = underOrganizationDTOS.stream()
                .map(underOrganizationMapper::toDepartmentUnderOrganization)
                .peek(department -> department.setOrganization(organization))
                .collect(Collectors.toList());

        departmentUnderOrganizationRepository.saveAll(departments);
        departments.forEach(department -> auditService.logAction(USERNAME, DEPARTMENT, CREATE, department.getId()));
    }

    @Override
    @Cacheable(value = "departmentCache", key = "#id")
    public DepartmentUnderBranchDTO getDepartmentUnderBranchById(Long id) {
        DepartmentUnderBranch department = departmentUnderBranchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + id));
        return departmentUnderBranchMapper.toDepartmentUnderBranchDTO(department);
    }

    @Override
    @Cacheable(value = "departmentCache", key = "#id")
    public DepartmentUnderOrganizationDTO getDepartmentUnderOrganizationById(Long id) {
        DepartmentUnderOrganization department = departmentUnderOrganizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + id));
        return underOrganizationMapper.toDepartmentUnderOrganizationDTO(department);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DepartmentUnderBranchDTO updateDepartmentUnderBranch(Long departmentId, DepartmentUnderBranchDTO departmentDTO) {
        DepartmentUnderBranch department = departmentUnderBranchRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + departmentId));

        departmentUnderBranchMapper.updateDepartmentUnderBranchFromDTO(departmentDTO, department);
        department.setUpdatedAt(LocalDateTime.now());
        DepartmentUnderBranch updatedDepartment = saveAndLog(department, UPDATE);
        return departmentUnderBranchMapper.toDepartmentUnderBranchDTO(updatedDepartment);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DepartmentUnderOrganizationDTO updateDepartmentUnderOrganization(Long departmentId, DepartmentUnderOrganizationDTO departmentDTO) {
        DepartmentUnderOrganization department = departmentUnderOrganizationRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + departmentId));

        underOrganizationMapper.updateDepartmentUnderOrganizationFromDTO(departmentDTO, department);
        department.setUpdatedAt(LocalDateTime.now());
        DepartmentUnderOrganization updatedDepartment = saveAndLog(department, UPDATE);
        return underOrganizationMapper.toDepartmentUnderOrganizationDTO(updatedDepartment);
    }

    @Override
    public List<DepartmentUnderBranchDTO> getDepartmentByBranch(Long branchId, Sort sort) {
        List<DepartmentUnderBranch> departments = departmentUnderBranchRepository.findByBranchId(branchId, sort);
        return departments.stream()
                .map(departmentUnderBranchMapper::toDepartmentUnderBranchDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentUnderOrganizationDTO> getDepartmentByOrganization(Long organizationId, Sort sort) {
        List<DepartmentUnderOrganization> departments = departmentUnderOrganizationRepository.findByOrganizationId(organizationId, sort);
        return departments.stream()
                .map(underOrganizationMapper::toDepartmentUnderOrganizationDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<DepartmentUnderBranchDTO> getAllDepartmentsUnderBranch(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DepartmentUnderBranch> departments = departmentUnderBranchRepository.findAll(pageable);
        if (page > departments.getTotalPages() - 1) {
            logService.log("Requested page exceeds total number of pages. Returning empty list.");
            return Collections.emptyList();
        }

        logService.log("Successfully retrieved all department information under branches.");
        return departments.stream()
                .map(departmentUnderBranchMapper::toDepartmentUnderBranchDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<DepartmentUnderOrganizationDTO> getAllDepartmentsUnderOrganization(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DepartmentUnderOrganization> departments = departmentUnderOrganizationRepository.findAll(pageable);
        if (page > departments.getTotalPages() - 1) {
            logService.log("Requested page exceeds total number of pages. Returning empty list.");
            return Collections.emptyList();
        }

        logService.log("Successfully retrieved all department information under organizations.");
        return departments.stream()
                .map(underOrganizationMapper::toDepartmentUnderOrganizationDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteDepartmentUnderBranch(Long id) {
        DepartmentUnderBranch department = departmentUnderBranchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + id));
        departmentUnderBranchRepository.delete(department);
        auditService.logAction(USERNAME, DEPARTMENT, "Delete", id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteDepartmentUnderOrganization(Long id) {
        DepartmentUnderOrganization department = departmentUnderOrganizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + id));
        departmentUnderOrganizationRepository.delete(department);
        auditService.logAction(USERNAME, DEPARTMENT, "Delete", id);
    }
}
