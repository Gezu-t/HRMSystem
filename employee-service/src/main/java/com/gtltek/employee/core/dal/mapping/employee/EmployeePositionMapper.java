package com.gtltek.employee.core.dal.mapping.employee;


import com.gtltek.employee.core.dal.dto.employee.EmployeePositionDTO;
import com.gtltek.employee.core.dal.model.employee.EmployeePosition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeePositionMapper {

    EmployeePositionMapper INSTANCE = Mappers.getMapper(EmployeePositionMapper.class);

    EmployeePosition toEmployeePosition(EmployeePositionDTO employeePositionDTO);

    EmployeePositionDTO toEmployeePositionDTO(EmployeePosition employeePosition);

}
