package com.hrmsystem.employeeprofileservice.dal.mapping.employee;


import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeDTO;
import com.hrmsystem.employeeservice.core.dal.model.employee.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO toEmployeeDTO(Employee employee);


    Employee toEmployee(EmployeeDTO employeeDTO);

    List<Employee> getListOfEmployee(List<EmployeeDTO> employeeDTOS);




}
