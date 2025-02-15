package com.hrmsystem.employeeservice.core.dal.mapping.branch;

import dal.dto.branch.BranchDTO;
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


    BranchDTO toBranchDTO(Branch entity);

    Branch toBranch(BranchDTO dto);

    void updateBranch(BranchDTO dto, @MappingTarget Branch entity);

    List<BranchDTO> toBranchDTOList(List<Branch> entities);

    @AfterMapping
    default void setOrganization(Branch entity, @MappingTarget BranchDTO dto) {
        if (entity.getOrganization() != null) {
            dto.setOrganizationId(entity.getOrganization().getId());
        }
    }

    @AfterMapping
    default void setOrganization(BranchDTO dto, @MappingTarget Branch entity) {
        if (dto.getOrganizationId() != null) {
            Organization organization = new Organization();
            organization.setId(dto.getOrganizationId());
            entity.setOrganization(organization);
        }
    }
}