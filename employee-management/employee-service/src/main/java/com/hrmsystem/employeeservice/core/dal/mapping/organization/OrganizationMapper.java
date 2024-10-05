package com.hrmsystem.employeeservice.core.dal.mapping.organization;

import com.hrmsystem.employeeservice.core.dal.mapping.common.AddressMapper;
import dal.dto.organization.OrganizationDTO;
import dal.model.organization.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {OwnersMapper.class, AddressMapper.class})
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);


    OrganizationDTO toOrganizationDTO(Organization entity);

    Organization toOrganization(OrganizationDTO dto);

    void updateOrganization(OrganizationDTO dto, @MappingTarget Organization entity);

//    @AfterMapping
//    default void mapBranchesAndDepartments(Organization entity, @MappingTarget OrganizationDTO dto) {
//        if (entity.getBranches() != null) {
//            dto.setBranches(BranchMapper.INSTANCE.toBranchDTOList(entity.getBranches()));
//        }
//        if (entity.getDepartments() != null) {
//            dto.setDepartments(DepartmentMapper.INSTANCE.toDepartmentDTOList(entity.getDepartments()));
//        }
//    }
}
