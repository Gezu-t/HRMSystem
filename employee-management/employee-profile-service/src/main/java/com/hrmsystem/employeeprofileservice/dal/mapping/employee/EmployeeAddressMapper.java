package com.hrmsystem.employeeprofileservice.dal.mapping.employee;


import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeAddressDTO;
import dal.model.employee.EmployeeAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeAddressMapper {


    EmployeeAddressMapper INSTANCE = Mappers.getMapper(EmployeeAddressMapper.class);


    EmployeeAddress toEmployeeAddress(EmployeeAddressDTO employeeAddressDTO);

    EmployeeAddressDTO toEmployeeAddressDTO(EmployeeAddress employeeAddress);



}
