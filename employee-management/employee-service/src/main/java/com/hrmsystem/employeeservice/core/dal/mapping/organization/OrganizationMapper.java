package com.hrmsystem.employeeservice.core.dal.mapping.organization;

import com.hrmsystem.employeeservice.core.dal.dto.organization.OrganizationDTO;
import com.hrmsystem.employeeservice.core.dal.mapping.branch.BranchMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.department.DepartmentMapper;
import dal.model.organization.Organization;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BranchMapper.class, DepartmentMapper.class})
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    @Mapping(target = "branches", ignore = true)
    @Mapping(target = "departments", ignore = true)
    OrganizationDTO toOrganizationDTO(Organization entity);

    @Mapping(target = "branches", ignore = true)
    @Mapping(target = "departments", ignore = true)
    Organization toOrganization(OrganizationDTO dto);

    @Mapping(target = "branches", ignore = true)
    @Mapping(target = "departments", ignore = true)
    void updateOrganization(OrganizationDTO dto, @MappingTarget Organization entity);

    @AfterMapping
    default void mapBranchesAndDepartments(Organization entity, @MappingTarget OrganizationDTO dto) {
        if (entity.getBranches() != null) {
            dto.setBranches(BranchMapper.INSTANCE.toBranchDTOList(entity.getBranches()));
        }
        if (entity.getDepartments() != null) {
            dto.setDepartments(DepartmentMapper.INSTANCE.toDepartmentDTOList(entity.getDepartments()));
        }
    }
}
