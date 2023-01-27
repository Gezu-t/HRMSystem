package et.hrms.dal.mapping;


import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    @Mapping(target = "id", source = "organizationId")
    @Mapping(target = "organizationAddress", source = "organizationAddressDTO")
    @Mapping(target = "organizationAddress.id", source = "organizationAddressDTO.addressId")
    Organization toOrganization(OrganizationDTO organizationDTO);

    @Mapping(target = "organizationId", source = "id")
    @Mapping(target = "organizationAddressDTO", source = "organizationAddress")
    @Mapping(target = "organizationAddressDTO.addressId", source = "organizationAddress.id")
    OrganizationDTO toOrganizationDTO(Organization organization);


}
