package et.hrms.service.impl;


import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.mapping.DepartmentMapper;
import et.hrms.dal.model.Branch;
import et.hrms.dal.model.Department;
import et.hrms.dal.model.Organization;
import et.hrms.dal.repository.BranchRepository;
import et.hrms.dal.repository.DepartmentRepository;
import et.hrms.dal.repository.OrganizationRepository;
import et.hrms.service.AuditService;
import et.hrms.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final String DEPARTMENT = "Department";
    private static final String USERNAME = "username";
    private static final String UPDATE = "Update";

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;
    private final AuditService auditService;

    private Department saveAndLog(Department department) {
        Department savedDepartment = departmentRepository.save(department);
        auditService.logAction(USERNAME, DEPARTMENT, UPDATE, savedDepartment.getId());
        return savedDepartment;
    }

    @Override
    public List<DepartmentDTO> createDepartmentByBranchId(long branchId, List<DepartmentDTO> departmentDTOs) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch information is not found by this id: " + branchId));

        List<Department> departments = new ArrayList<>();

        for (DepartmentDTO departmentDTO : departmentDTOs) {
            Department department = departmentMapper.toDepartment(departmentDTO);
            if (department != null) {
                department.setBranch(branch);
                departments.add(department);
            }
        }

        List<Department> savedDepartments = departmentRepository.saveAll(departments);
        List<DepartmentDTO> savedDepartmentDTOs = new ArrayList<>();
        for (Department savedDepartment : savedDepartments) {
            savedDepartmentDTOs.add(departmentMapper.toDepartmentDTO(savedDepartment));
        }

        return savedDepartmentDTOs;
    }


    @Override
    @Transactional
    public List<DepartmentDTO> createDepartmentByOrganizationId(Long organizationId, List<DepartmentDTO> departmentDTOs) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(EntityNotFoundException::new);

        List<Department> departments = new ArrayList<>();

        for (DepartmentDTO departmentDTO : departmentDTOs) {
            Department department = departmentMapper.toDepartment(departmentDTO);
            department.setOrganization(organization);
            departments.add(department);
        }

        List<Department> savedDepartments = departmentRepository.saveAll(departments);
        List<DepartmentDTO> savedDepartmentDTOs = new ArrayList<>();
        for (Department savedDepartment : savedDepartments) {
            savedDepartmentDTOs.add(departmentMapper.toDepartmentDTO(savedDepartment));
        }

        return savedDepartmentDTOs;
    }

    @Override
    @Cacheable(value = "departmentCache", key = "#id")
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return departmentMapper.toDepartmentDTO(department);
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentDTO.getDepartmentId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Department information is not found by this id: " + departmentDTO.getDepartmentId()));

        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setLocations(departmentDTO.getLocations());

        Branch branch = branchRepository.findById(departmentDTO.getBranchId())
                .orElseThrow(() -> new EntityNotFoundException("Branch is not found by this id: " + departmentDTO.getBranchId()));

        department.setBranch(branch);
        department.setUpdatedAt(LocalDateTime.now());

        Department updatedDepartment = saveAndLog(department);
        return departmentMapper.toDepartmentDTO(updatedDepartment);
    }

    private List<DepartmentDTO> mapToDepartmentDTOList(List<Department> departments) {
        return departments.stream()
                .map(departmentMapper::toDepartmentDTO)
                .toList();
    }

    @Override
    public List<DepartmentDTO> getDepartmentByOrganization(Long organizationId, Sort sort) {
        List<Department> departments = departmentRepository.findByOrganizationId(organizationId, sort);
        return mapToDepartmentDTOList(departments);
    }

    @Override
    public List<DepartmentDTO> getDepartmentByBranch(Long branchId, Sort sort) {
        List<Department> departments = departmentRepository.findByBranchId(branchId, sort);
        return mapToDepartmentDTOList(departments);
    }

    @Override
    public List<DepartmentDTO> getAllDepartment(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departments = departmentRepository.findAll(pageable);

        return mapToDepartmentDTOList(departments.getContent());
    }


}