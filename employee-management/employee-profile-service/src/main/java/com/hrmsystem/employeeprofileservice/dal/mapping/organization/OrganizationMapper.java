package com.hrmsystem.employeeprofileservice.dal.mapping.organization;


import com.hrmsystem.employeeprofileservice.dal.dto.department.OrganizationDTO;
import com.hrmsystem.employeeservice.core.dal.model.organization.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "organizationAddress", source = "organizationAddressDTO")
    Organization toOrganization(OrganizationDTO organizationDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "organizationAddressDTO", source = "organizationAddress")
    OrganizationDTO toOrganizationDTO(Organization organization);

    List<OrganizationDTO> toEntities(List<Organization> organizations);


}
