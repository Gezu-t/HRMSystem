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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    public static final String DEPARTMENT = "Department";
    public static final String USERNAME = "username";
    public static final String UPDATE = "Update";
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;
    private final AuditService auditService;


    @Override
    public List<DepartmentDTO> createDepartmentByBranchId(long branchId, DepartmentDTO departmentDTO) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch information is not found by this id: " + branchId));

        Department department = departmentMapper.toDepartment(departmentDTO);
        Set<Department> departments = new LinkedHashSet<>();

        department.setBranch(branch);
        departments.add(department);
        List<Department> departmentBranches= departmentRepository.saveAll(departments);

        for(Department depBranch: departmentBranches){
            auditService.logAction(USERNAME, DEPARTMENT, UPDATE, depBranch.getId());
        }

        return departmentMapper.toDepartmentList(departmentBranches);
    }


    @Override
    public Set<DepartmentDTO> createDepartmentByOrganizationId(Long organizationId, DepartmentDTO departmentDTO) {

        Set<Department> departments = new LinkedHashSet<>();
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Organization is not found by this name{0}: ", organizationId)));

        Department department = departmentMapper.toDepartment(departmentDTO);
        department.setOrganization(organization);
        departments.add(department);

        List<Department> departmentList = departmentRepository.saveAll(departmentRepository.saveAll(departments));
        for(Department dep: departmentList) {
            auditService.logAction(USERNAME, DEPARTMENT, UPDATE, dep.getId());
        }
        Set<Department> departmentSet = Set.copyOf(departmentList);
        return departmentMapper.toDepartmentSet(departmentSet);
    }


    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return departmentMapper.toDepartmentDTO(department);
    }


    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {

        Department department = departmentRepository.findById(departmentDTO.getDepartmentId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                MessageFormat.format("Department information is not found by this id {0}."
                                        , departmentDTO.getDepartmentId())));

        // update the fields of the education record with the new values from the DTO
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setLocations(departmentDTO.getLocations());
        Branch branch = branchRepository.findById(departmentDTO.getBranchId())
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Branch is not found by this id {0}. ", departmentDTO.getBranchId())));
        department.setBranch(branch);
        // add updated date for current information
        department.setUpdatedAt(LocalDateTime.now());
        // save the updated department record to the database
        department = departmentRepository.save(department);
        auditService.logAction(USERNAME, DEPARTMENT, UPDATE, department.getId());
        // map the updated department record to a DTO and return it
        return departmentMapper.toDepartmentDTO(department);
    }

    @Override
    public List<DepartmentDTO> getDepartmentByOrganization(Long organizationId, Sort sort) {
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        List<Department> departments = departmentRepository.findByOrganizationId(organizationId, sort);
        if (!departments.isEmpty()) {
            for (Department department : departments) {
                DepartmentDTO departmentDTO = departmentMapper.toDepartmentDTO(department);
                departmentDTOS.add(departmentDTO);
            }
        } else {
            throw new EntityNotFoundException(MessageFormat.format("Department information is not found by this organization Id {0}.", organizationId));
        }
        return departmentDTOS;
    }

    @Override
    public List<DepartmentDTO> getDepartmentByBranch(Long branchId, Sort sort) {
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        List<Department> departments = departmentRepository.findByBranchId(branchId, sort);
        if (!departments.isEmpty()) {
            for (Department department : departments) {
                DepartmentDTO departmentDTO = departmentMapper.toDepartmentDTO(department);
                departmentDTOS.add(departmentDTO);
            }
        } else {
            throw new EntityNotFoundException(MessageFormat.format("Department information is not found by this branch Id {0}.", branchId));
        }
        return departmentDTOS;
    }


    @Override
    public List<DepartmentDTO> getAllDepartment(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departments = departmentRepository.findAll(pageable);
        return departments.stream()
                .map(departmentMapper::toDepartmentDTO)
                .toList();
    }


}
