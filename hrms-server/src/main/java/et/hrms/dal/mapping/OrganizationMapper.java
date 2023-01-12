package et.hrms.dal.mapping;


import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    Organization toOrganization(OrganizationDTO organizationDTO);

    OrganizationDTO toOrganizationDTO(Organization organization);



}
