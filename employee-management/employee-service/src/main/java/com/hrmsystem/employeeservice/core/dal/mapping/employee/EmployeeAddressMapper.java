package com.hrmsystem.employeeservice.core.dal.mapping.employee;


import dal.dto.employee.EmployeeAddressDTO;
import dal.model.employee.EmployeeAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeAddressMapper {


    EmployeeAddressMapper INSTANCE = Mappers.getMapper(EmployeeAddressMapper.class);

}
