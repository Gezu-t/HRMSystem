package com.hrmsystem.employeeservice.core.dal.mapping.employee;


import com.hrmsystem.employeeservice.core.dal.dto.employee.EmployeeAppearanceDTO;
import dal.model.employee.EmployeeAppearance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeAppearanceMapper {

    EmployeeAppearanceMapper INSTANCE = Mappers.getMapper(EmployeeAppearanceMapper.class);


    @Mapping(target = "employee.id", source = "employeeId")
    EmployeeAppearance toAppearance(EmployeeAppearanceDTO employeeAppearanceDTO);

    @Mapping(target = "employeeId", source = "employee.id")
    EmployeeAppearanceDTO toAppearanceDTO(EmployeeAppearance employeeAppearance);


}
