package com.gtltek.employee.core.dal.mapping.employee;


import com.gtltek.employee.core.dal.dto.employee.EmployeeEvaluationDTO;
import com.gtltek.employee.core.dal.model.employee.EmployeeEvaluation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeEvaluationMapper {


    EmployeeEvaluationMapper INSTANCE = Mappers.getMapper(EmployeeEvaluationMapper.class);

    EmployeeEvaluation toEmployeeEvaluation(EmployeeEvaluationDTO employeeEvaluationDTO);

    EmployeeEvaluationDTO toEmployeeEvaluation(EmployeeEvaluation employeeEvaluation);


}
