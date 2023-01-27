package et.hrms.dal.mapping;

import et.hrms.dal.dto.OrganizationAddressDTO;
import et.hrms.dal.model.OrganizationAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface OrganizationAddressMapper {


    OrganizationAddressMapper INSTANCE = Mappers.getMapper(OrganizationAddressMapper.class);

    @Mapping(target = "id", source = "addressId")
    OrganizationAddress toOrganizationAddress(OrganizationAddressDTO organizationAddressDTO);

    @Mapping(target = "addressId", source = "id")
    OrganizationAddressDTO toOrganizationAddressDTO(OrganizationAddress organizationAddress);




}
