package com.hrmsystem.employeeservice.core.dal.mapping.branch;

import com.hrmsystem.employeeservice.core.dal.dto.branch.BranchDTO;
import dal.model.branch.Branch;
import dal.model.organization.Organization;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "organizationName", source = "organization.organizationName")
    BranchDTO toBranchDTO(Branch entity);

    @Mapping(target = "organization", ignore = true)
    Branch toBranch(BranchDTO dto);

    @Mapping(target = "organization", ignore = true)
    void updateBranch(BranchDTO dto, @MappingTarget Branch entity);

    List<BranchDTO> toBranchDTOList(List<Branch> entities);

    @AfterMapping
    default void setOrganization(Branch entity, @MappingTarget BranchDTO dto) {
        if (entity.getOrganization() != null) {
            dto.setOrganizationId(entity.getOrganization().getId());
            dto.setOrganizationName(entity.getOrganization().getOrganizationName());
        }
    }

    @AfterMapping
    default void setOrganization(BranchDTO dto, @MappingTarget Branch entity) {
        if (dto.getOrganizationId() != null) {
            Organization organization = new Organization();
            organization.setId(dto.getOrganizationId());
            organization.setOrganizationName(dto.getOrganizationName());
            entity.setOrganization(organization);
        }
    }
}