package et.hrms.service.impl;


import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.mapping.DepartmentMapper;
import et.hrms.dal.mapping.OrganizationMapper;
import et.hrms.dal.model.Department;
import et.hrms.dal.model.Organization;
import et.hrms.dal.repository.DepartmentRepository;
import et.hrms.service.DepartmentService;
import et.hrms.service.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    private final OrganizationMapper organizationMapper;

    private final OrganizationService organizationService;


    @Override
    public Department createDepartment(DepartmentDTO departmentDTO,
                                       OrganizationDTO organizationDTO) throws Exception {

        Department department = departmentMapper.toDepartment(departmentDTO);

        OrganizationDTO organizationDTOdb = organizationService.getOrganizationById(organizationDTO.getOrganizationId());

        Organization organization = organizationMapper.toOrganization(organizationDTOdb);

        department.setOrganization(organization);

        departmentRepository.save(department);

        return department;
    }


    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return departmentMapper.toDepartmentDTO(department);
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) throws Exception {

        Department department = departmentRepository.findById(departmentDTO.getDepartmentId())
                .orElseThrow(EntityNotFoundException::new);

        // update the fields of the education record with the new values from the DTO
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setLocations(departmentDTO.getLocations());

        OrganizationDTO organizationDTO = organizationService.getOrganizationById(department.getOrganization().getId());
        Organization organization = organizationMapper.toOrganization(organizationDTO);
        department.setOrganization(organization);


        // add updated date for current information
        department.setUpdatedAt(LocalDateTime.now());

        // save the updated department record to the database
        department = departmentRepository.save(department);

        // map the updated department record to a DTO and return it
        return departmentMapper.toDepartmentDTO(department);
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
