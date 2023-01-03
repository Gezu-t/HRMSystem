package et.hrms.service.impl;


import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.model.Department;
import et.hrms.dal.model.Organization;
import et.hrms.dal.repository.DepartmentRepository;
import et.hrms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;




    @Override
    public Department createDepartment(DepartmentDTO departmentDTO) {

        Department department = new Department();
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentNumber(departmentDTO.getDepartmentId());
        department.setLocations(departmentDTO.getLocations());
        Organization organization = new Organization();
        organization.setId(department.getOrganization().getId());
        department.setOrganization(organization);


        departmentRepository.save(department);

        return department;
    }


    @Override
    public List<DepartmentDTO> getAllDepartment(){

        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        DepartmentDTO departmentDTO = new DepartmentDTO();
        List<Department> departments = departmentRepository.findAll();
        for (Department department: departments){
            departmentDTO.setDepartmentId(department.getDepartmentNumber());
            departmentDTO.setDepartmentName(department.getDepartmentName());
            departmentDTO.setLocations(department.getLocations());
            departmentDTO.setOrganizationId(department.getOrganization().getId());
            departmentDTOS.add(departmentDTO);
        }

        return departmentDTOS;
    }




}
