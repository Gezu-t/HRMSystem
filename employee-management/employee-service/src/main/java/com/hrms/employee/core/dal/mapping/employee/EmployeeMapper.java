package com.hrms.employee.core.dal.mapping.employee;

import com.hrms.employee.core.dal.dto.employee.EmployeeDTO;
import com.hrms.employee.core.dal.mapping.education.EducationMapper;
import com.hrms.employee.core.dal.model.employee.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        EmployeeAppearanceMapper.class,
        FamilyMapper.class,
        EmployeeEvaluationMapper.class,
        EmployeeAddressMapper.class,
        EmployeeDetailMapper.class,
        EmployeePromotionMapper.class,
        EducationMapper.class
})
public interface EmployeeMapper {

    EmployeeDTO toEmployeeDTO(Employee employee);


    Employee toEmployee(EmployeeDTO employeeDTO);

    void updateEmployee(EmployeeDTO employeeDTO, @MappingTarget Employee employee);


}
