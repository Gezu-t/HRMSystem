package com.gtltek.employee.core.dal.mapping.employee;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeAddressMapper {


    EmployeeAddressMapper INSTANCE = Mappers.getMapper(EmployeeAddressMapper.class);

}
