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

    Address toAddress(AddressDTO addressDTO);

    AddressDTO toAddressDTO(Address address);
    void updateAddress(AddressDTO addressDTO, @MappingTarget Address address);
}
