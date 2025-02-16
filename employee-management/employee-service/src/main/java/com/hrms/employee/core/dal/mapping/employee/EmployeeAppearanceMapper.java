package com.hrms.employee.core.dal.mapping.employee;


import com.hrms.employee.core.dal.dto.employee.EmployeeAppearanceDTO;
import com.hrms.employee.core.dal.model.employee.EmployeeAppearance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeAppearanceMapper {

    EmployeeAppearanceMapper INSTANCE = Mappers.getMapper(EmployeeAppearanceMapper.class);


    EmployeeAppearance toAppearance(EmployeeAppearanceDTO employeeAppearanceDTO);

    EmployeeAppearanceDTO toAppearanceDTO(EmployeeAppearance employeeAppearance);


}
