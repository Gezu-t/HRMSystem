package et.hrms.dal.mapping.department;

import et.hrms.dal.dto.department.OrganizationAddressDTO;
import et.hrms.dal.model.department.OrganizationAddress;
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