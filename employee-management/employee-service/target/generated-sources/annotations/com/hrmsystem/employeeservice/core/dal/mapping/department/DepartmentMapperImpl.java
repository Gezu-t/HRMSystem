package com.hrmsystem.employeeservice.core.dal.mapping.department;

import dal.dto.department.DepartmentDTO;
import dal.model.branch.Branch;
import dal.model.department.Department;
import dal.model.organization.Organization;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDTO toDepartmentDTO(Department entity) {
        if ( entity == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setBranchId( entityBranchId( entity ) );
        departmentDTO.setOrganizationId( entityOrganizationId( entity ) );
        departmentDTO.setId( entity.getId() );
        departmentDTO.setDepartmentNumber( entity.getDepartmentNumber() );
        departmentDTO.setDepartmentName( entity.getDepartmentName() );
        departmentDTO.setLocations( entity.getLocations() );

        setAdditionalFields( entity, departmentDTO );

        return departmentDTO;
    }

    @Override
    public Department toDepartment(DepartmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( dto.getId() );
        department.setDepartmentNumber( dto.getDepartmentNumber() );
        department.setDepartmentName( dto.getDepartmentName() );
        department.setLocations( dto.getLocations() );

        setBranchAndOrganization( dto, department );

        return department;
    }

    @Override
    public void updateDepartment(DepartmentDTO dto, Department entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setDepartmentNumber( dto.getDepartmentNumber() );
        entity.setDepartmentName( dto.getDepartmentName() );
        entity.setLocations( dto.getLocations() );

        setBranchAndOrganization( dto, entity );
    }

    @Override
    public List<DepartmentDTO> toDepartmentDTOList(List<Department> entities) {
        if ( entities == null ) {
            return null;
        }

        List<DepartmentDTO> list = new ArrayList<DepartmentDTO>( entities.size() );
        for ( Department department : entities ) {
            list.add( toDepartmentDTO( department ) );
        }

        return list;
    }

    private Long entityBranchId(Department department) {
        if ( department == null ) {
            return null;
        }
        Branch branch = department.getBranch();
        if ( branch == null ) {
            return null;
        }
        Long id = branch.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityOrganizationId(Department department) {
        if ( department == null ) {
            return null;
        }
        Organization organization = department.getOrganization();
        if ( organization == null ) {
            return null;
        }
        Long id = organization.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
