package com.hrmsystem.employeeservice.core.dal.mapping.employee;


import dal.dto.employee.EmployeeAppearanceDTO;
import dal.model.employee.EmployeeAppearance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeAppearanceMapper {

    EmployeeAppearanceMapper INSTANCE = Mappers.getMapper(EmployeeAppearanceMapper.class);


    EmployeeAppearance toAppearance(EmployeeAppearanceDTO employeeAppearanceDTO);

    EmployeeAppearanceDTO toAppearanceDTO(EmployeeAppearance employeeAppearance);


}
