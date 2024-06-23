package com.hrmsystem.employeeprofileservice.dal.mapping.organization;

import com.hrmsystem.employeeprofileservice.dal.dto.department.OrganizationAddressDTO;
import com.hrmsystem.employeeservice.core.dal.model.organization.OrganizationAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface OrganizationAddressMapper {

    OrganizationAddressMapper INSTANCE = Mappers.getMapper(OrganizationAddressMapper.class);

    @Mapping(target = "id", source = "id")
    OrganizationAddress toOrganizationAddress(OrganizationAddressDTO organizationAddressDTO);

    @Mapping(target = "id", source = "id")
    OrganizationAddressDTO toOrganizationAddressDTO(OrganizationAddress organizationAddress);

    void updateOrganizationAddressFromDto(OrganizationAddressDTO organizationAddressDTO,
                                          @MappingTarget OrganizationAddress existingOrganizationAddress);

}