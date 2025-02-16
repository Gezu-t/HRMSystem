package com.hrms.employee.core.dal.mapping.common;

import com.hrms.employee.core.dal.dto.common.AddressDTO;
import com.hrms.employee.core.dal.model.branch.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toAddress(AddressDTO addressDTO);

    AddressDTO toAddressDTO(Address address);
    void updateAddress(AddressDTO addressDTO, @MappingTarget Address address);
}
