package com.hrmsystem.employeeservice.core.dal.mapping.common;

import dal.dto.common.AddressDTO;
import dal.model.branch.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    // Mapping from DTO to Entity
    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "branch", ignore = true)
    Address toAddress(AddressDTO addressDTO);

    // Mapping from Entity to DTO
    AddressDTO toAddressDTO(Address address);

    // Updating existing Address entity from DTO
    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "branch", ignore = true)
    void updateAddress(AddressDTO addressDTO, @MappingTarget Address address);
}
