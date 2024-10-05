package com.hrmsystem.employeeservice.core.dal.mapping.employee;


import dal.dto.employee.EmployeeEvaluationDTO;
import dal.model.employee.EmployeeEvaluation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EmployeeEvaluationMapper {


    EmployeeEvaluationMapper INSTANCE = Mappers.getMapper(EmployeeEvaluationMapper.class);


    EmployeeEvaluation toEmployeeEvaluation(EmployeeEvaluationDTO employeeEvaluationDTO);
    EmployeeEvaluationDTO toEmployeeEvaluation(EmployeeEvaluation employeeEvaluation);


}
