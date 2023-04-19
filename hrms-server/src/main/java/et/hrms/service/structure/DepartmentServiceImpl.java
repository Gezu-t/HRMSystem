package et.hrms.service.structure;


import et.hrms.dal.dto.structure.DepartmentDTO;
import et.hrms.dal.mapping.DepartmentMapper;
import et.hrms.dal.model.structure.Branch;
import et.hrms.dal.model.structure.Department;
import et.hrms.dal.model.structure.Organization;
import et.hrms.dal.repository.structure.BranchRepository;
import et.hrms.dal.repository.structure.DepartmentRepository;
import et.hrms.dal.repository.structure.OrganizationRepository;
import et.hrms.service.log.AuditService;
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
    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createDepartmentByBranchId(long branchId, List<DepartmentDTO> savedDepartmentDTOS) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch information is not found by this id: " + branchId));
        List<Department> departments = new ArrayList<>();
        for (DepartmentDTO departmentDTO : savedDepartmentDTOS) {
            Department department = departmentMapper.toDepartment(departmentDTO);
            if (department != null) {
                department.setBranch(branch);
                departments.add(department);
            }
            auditService.logAction("username", "Department", "Create", department.getId());
            departments.add(department);
        }
        departmentMapper.toDepartmentDTOs(departmentRepository.saveAll(departments));
    }


    @Override
    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createDepartmentByOrganizationId(Long organizationId, List<DepartmentDTO> departmentDTOs) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(EntityNotFoundException::new);

        List<Department> departments = new ArrayList<>();

        for (DepartmentDTO departmentDTO : departmentDTOs) {
            Department department = departmentMapper.toDepartment(departmentDTO);
            if (department != null) {
                department.setOrganization(organization);
            }
            auditService.logAction("username", "Department ", "Create", department.getId());
            departments.add(department);
        }
        departmentMapper.toDepartmentDTOs(departmentRepository.saveAll(departments));
    }

    @Override
    @Cacheable(value = "departmentCache", key = "#id")
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return departmentMapper.toDepartmentDTO(department);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Department information is not found by this id: " + departmentId));

        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setLocations(departmentDTO.getLocations());

       if(department.getBranch() != null) {
           Branch branch = new Branch();
           branch.setId(departmentDTO.getBranchId());
           department.setBranch(branch);
       } else {
           Organization organization = new Organization();
           organization.setId(departmentDTO.getOrganizationId());
       }

//        Branch branch = branchRepository.findById(departmentDTO.getBranchId())
//                .orElseThrow(() -> new EntityNotFoundException("Branch is not found by this id: " + departmentDTO.getBranchId()));
//
//        department.setBranch(branch);
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