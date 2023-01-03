package et.hrms.dal.mapping;


import et.hrms.dal.dto.EmployeeEvaluationDTO;
import et.hrms.dal.model.EmployeeEvaluation;
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
