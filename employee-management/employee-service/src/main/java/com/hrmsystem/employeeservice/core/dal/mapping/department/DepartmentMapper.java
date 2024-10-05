package com.hrmsystem.employeeservice.core.dal.mapping.department;

import dal.dto.department.DepartmentDTO;
import dal.model.branch.Branch;
import dal.model.department.Department;
import dal.model.organization.Organization;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(target = "branchId", source = "branch.id")
    @Mapping(target = "organizationId", source = "organization.id")
    DepartmentDTO toDepartmentDTO(Department entity);

    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "organization", ignore = true)
    Department toDepartment(DepartmentDTO dto);

    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "organization", ignore = true)
    void updateDepartment(DepartmentDTO dto, @MappingTarget Department entity);

    List<DepartmentDTO> toDepartmentDTOList(List<Department> entities);

    @AfterMapping
    default void setBranchAndOrganization(DepartmentDTO dto, @MappingTarget Department entity) {
        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());
            entity.setBranch(branch);

            // If department is under a branch, set the organization from the branch
            Organization organization = new Organization();
            organization.setId(dto.getOrganizationId());
            branch.setOrganization(organization);
            entity.setOrganization(organization);
        } else if (dto.getOrganizationId() != null) {
            Organization organization = new Organization();
            organization.setId(dto.getOrganizationId());
            entity.setOrganization(organization);

            // If department is directly under an organization, ensure branch is null
            entity.setBranch(null);
        }
    }

    @AfterMapping
    default void setAdditionalFields(Department entity, @MappingTarget DepartmentDTO dto) {
        if (entity.getBranch() != null) {
            if (entity.getBranch().getOrganization() != null) {
                dto.setOrganizationId(entity.getBranch().getOrganization().getId());
            }
        }
    }
}