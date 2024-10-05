package com.hrmsystem.employeeservice.core.dal.mapping.branch;

import dal.dto.branch.BranchDTO;
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
public class BranchMapperImpl implements BranchMapper {

    @Override
    public BranchDTO toBranchDTO(Branch entity) {
        if ( entity == null ) {
            return null;
        }

        BranchDTO branchDTO = new BranchDTO();

        branchDTO.setOrganizationId( entityOrganizationId( entity ) );
        branchDTO.setId( entity.getId() );
        branchDTO.setBranchCode( entity.getBranchCode() );
        branchDTO.setBranchName( entity.getBranchName() );
        branchDTO.setDepartments( departmentListToDepartmentDTOList( entity.getDepartments() ) );

        setOrganization( entity, branchDTO );

        return branchDTO;
    }

    @Override
    public Branch toBranch(BranchDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Branch branch = new Branch();

        branch.setId( dto.getId() );
        branch.setBranchCode( dto.getBranchCode() );
        branch.setBranchName( dto.getBranchName() );
        branch.setDepartments( departmentDTOListToDepartmentList( dto.getDepartments() ) );

        setOrganization( dto, branch );

        return branch;
    }

    @Override
    public void updateBranch(BranchDTO dto, Branch entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setBranchCode( dto.getBranchCode() );
        entity.setBranchName( dto.getBranchName() );
        if ( entity.getDepartments() != null ) {
            List<Department> list = departmentDTOListToDepartmentList( dto.getDepartments() );
            if ( list != null ) {
                entity.getDepartments().clear();
                entity.getDepartments().addAll( list );
            }
            else {
                entity.setDepartments( null );
            }
        }
        else {
            List<Department> list = departmentDTOListToDepartmentList( dto.getDepartments() );
            if ( list != null ) {
                entity.setDepartments( list );
            }
        }

        setOrganization( dto, entity );
    }

    @Override
    public List<BranchDTO> toBranchDTOList(List<Branch> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BranchDTO> list = new ArrayList<BranchDTO>( entities.size() );
        for ( Branch branch : entities ) {
            list.add( toBranchDTO( branch ) );
        }

        return list;
    }

    private Long entityOrganizationId(Branch branch) {
        if ( branch == null ) {
            return null;
        }
        Organization organization = branch.getOrganization();
        if ( organization == null ) {
            return null;
        }
        Long id = organization.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected DepartmentDTO departmentToDepartmentDTO(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setId( department.getId() );
        departmentDTO.setDepartmentNumber( department.getDepartmentNumber() );
        departmentDTO.setDepartmentName( department.getDepartmentName() );
        departmentDTO.setLocations( department.getLocations() );

        return departmentDTO;
    }

    protected List<DepartmentDTO> departmentListToDepartmentDTOList(List<Department> list) {
        if ( list == null ) {
            return null;
        }

        List<DepartmentDTO> list1 = new ArrayList<DepartmentDTO>( list.size() );
        for ( Department department : list ) {
            list1.add( departmentToDepartmentDTO( department ) );
        }

        return list1;
    }

    protected Department departmentDTOToDepartment(DepartmentDTO departmentDTO) {
        if ( departmentDTO == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( departmentDTO.getId() );
        department.setDepartmentNumber( departmentDTO.getDepartmentNumber() );
        department.setDepartmentName( departmentDTO.getDepartmentName() );
        department.setLocations( departmentDTO.getLocations() );

        return department;
    }

    protected List<Department> departmentDTOListToDepartmentList(List<DepartmentDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Department> list1 = new ArrayList<Department>( list.size() );
        for ( DepartmentDTO departmentDTO : list ) {
            list1.add( departmentDTOToDepartment( departmentDTO ) );
        }

        return list1;
    }
}
