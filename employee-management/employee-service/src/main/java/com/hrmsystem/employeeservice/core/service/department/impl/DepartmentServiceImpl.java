package com.hrmsystem.employeeservice.core.service.department.impl;

import dal.dto.department.DepartmentDTO;
import com.hrmsystem.employeeservice.core.dal.mapping.department.DepartmentMapper;
import com.hrmsystem.employeeservice.core.service.department.DepartmentService;
import com.hrmsystem.employeeservice.core.service.log.AuditService;
import com.hrmsystem.employeeservice.core.service.log.LogService;
import dal.model.branch.Branch;
import dal.model.department.Department;
import dal.model.organization.Organization;
import dal.repository.branch.BranchRepository;
import dal.repository.department.DepartmentRepository;
import dal.repository.organization.OrganizationRepository;
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
    private static final String DELETE = "Delete";

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;
    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;
    private final AuditService auditService;
    private final LogService logService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO, Long branchId, Long organizationId) {
        Department department = departmentMapper.toDepartment(departmentDTO);

        if (branchId != null) {
            Branch branch = branchRepository.findById(branchId)
                    .orElseThrow(() -> new EntityNotFoundException("Branch not found by id: " + branchId));
            department.setBranch(branch);
            department.setOrganization(branch.getOrganization());
        } else if (organizationId != null) {
            Organization organization = organizationRepository.findById(organizationId)
                    .orElseThrow(() -> new EntityNotFoundException("Organization not found by id: " + organizationId));
            department.setOrganization(organization);
        } else {
            throw new IllegalArgumentException("Either branchId or organizationId must be provided");
        }

        Department savedDepartment = departmentRepository.save(department);
        auditService.logAction(USERNAME, DEPARTMENT, CREATE, savedDepartment.getId());
        return departmentMapper.toDepartmentDTO(savedDepartment);
    }

    @Override
    @Cacheable(value = "departmentCache", key = "#id")
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + id));
        return departmentMapper.toDepartmentDTO(department);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + departmentId));

        departmentMapper.updateDepartmentFromDTO(departmentDTO, department);
        department.setUpdatedAt(LocalDateTime.now());
        Department updatedDepartment = departmentRepository.save(department);
        auditService.logAction(USERNAME, DEPARTMENT, UPDATE, updatedDepartment.getId());
        return departmentMapper.toDepartmentDTO(updatedDepartment);
    }

    @Override
    public List<DepartmentDTO> getDepartmentsByBranch(Long branchId, Sort sort) {
        List<Department> departments = departmentRepository.findByBranchId(branchId, sort);
        return departments.stream()
                .map(departmentMapper::toDepartmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDTO> getDepartmentsByOrganization(Long organizationId, Sort sort) {
        List<Department> departments = departmentRepository.findByOrganizationIdAndBranchIsNull(organizationId, sort);
        return departments.stream()
                .map(departmentMapper::toDepartmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDTO> getAllDepartments(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Department> departments = departmentRepository.findAll(pageable);
        if (page > departments.getTotalPages() - 1) {
            logService.log("Requested page exceeds total number of pages. Returning empty list.");
            return Collections.emptyList();
        }

        logService.log("Successfully retrieved all department information.");
        return departments.stream()
                .map(departmentMapper::toDepartmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + id));
        departmentRepository.delete(department);
        auditService.logAction(USERNAME, DEPARTMENT, DELETE, id);
    }

   /* @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        // Find the existing department entity by ID
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found by id: " + departmentId));

        // Update the department entity using the mapper and data from departmentDTO
        departmentMapper.updateDepartmentFromDTO(departmentDTO, department);

        // Update the timestamp
        department.setUpdatedAt(LocalDateTime.now());

        // Save the updated entity to the database
        Department updatedDepartment = departmentRepository.save(department);

        // Convert the updated entity to a DTO and return it
        return departmentMapper.toDepartmentDTO(updatedDepartment);
    }*/

}
