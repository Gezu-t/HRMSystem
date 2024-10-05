package com.hrmsystem.employeeservice.core.dal.mapping.employee;

import dal.dto.employee.EmployeeEvaluationDTO;
import dal.model.employee.EmployeeEvaluation;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EmployeeEvaluationMapperImpl implements EmployeeEvaluationMapper {

    @Override
    public EmployeeEvaluation toEmployeeEvaluation(EmployeeEvaluationDTO employeeEvaluationDTO) {
        if ( employeeEvaluationDTO == null ) {
            return null;
        }

        EmployeeEvaluation employeeEvaluation = new EmployeeEvaluation();

        employeeEvaluation.setId( employeeEvaluationDTO.getId() );
        employeeEvaluation.setEvaluationDate( employeeEvaluationDTO.getEvaluationDate() );
        employeeEvaluation.setComment( employeeEvaluationDTO.getComment() );
        employeeEvaluation.setResult( employeeEvaluationDTO.getResult() );
        employeeEvaluation.setPrimaryVote( employeeEvaluationDTO.getPrimaryVote() );
        employeeEvaluation.setSecondaryVote( employeeEvaluationDTO.getSecondaryVote() );
        employeeEvaluation.setEvaluationStatus( employeeEvaluationDTO.getEvaluationStatus() );

        return employeeEvaluation;
    }

    @Override
    public EmployeeEvaluationDTO toEmployeeEvaluation(EmployeeEvaluation employeeEvaluation) {
        if ( employeeEvaluation == null ) {
            return null;
        }

        EmployeeEvaluationDTO employeeEvaluationDTO = new EmployeeEvaluationDTO();

        employeeEvaluationDTO.setId( employeeEvaluation.getId() );
        employeeEvaluationDTO.setEvaluationDate( employeeEvaluation.getEvaluationDate() );
        employeeEvaluationDTO.setComment( employeeEvaluation.getComment() );
        employeeEvaluationDTO.setResult( employeeEvaluation.getResult() );
        employeeEvaluationDTO.setPrimaryVote( employeeEvaluation.getPrimaryVote() );
        employeeEvaluationDTO.setSecondaryVote( employeeEvaluation.getSecondaryVote() );
        employeeEvaluationDTO.setEvaluationStatus( employeeEvaluation.getEvaluationStatus() );

        return employeeEvaluationDTO;
    }
}
