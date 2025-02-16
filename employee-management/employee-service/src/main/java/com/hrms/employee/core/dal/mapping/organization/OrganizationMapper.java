package com.hrms.employee.core.dal.mapping.organization;

import com.hrms.employee.core.dal.mapping.common.AddressMapper;
import com.hrms.employee.core.dal.dto.organization.OrganizationDTO;
import com.hrms.employee.core.dal.model.organization.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {OwnersMapper.class, AddressMapper.class})
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    OrganizationDTO toOrganizationDTO(Organization organization);

    Organization toOrganization(OrganizationDTO organizationDTO);

    void updateOrganization(OrganizationDTO dto, @MappingTarget Organization entity);


}
