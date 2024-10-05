package com.hrmsystem.employeeservice.core.dal.mapping.organization;

import com.hrmsystem.employeeservice.core.dal.mapping.common.AddressMapper;
import dal.dto.organization.OrganizationDTO;
import dal.model.organization.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {OwnersMapper.class, AddressMapper.class})
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    @Mapping(source = "addresses", target = "addresses")
    OrganizationDTO toOrganizationDTO(Organization organization);

    @Mapping(source = "addresses", target = "addresses")
    Organization toOrganization(OrganizationDTO organizationDTO);

    void updateOrganization(OrganizationDTO dto, @MappingTarget Organization entity);


}
