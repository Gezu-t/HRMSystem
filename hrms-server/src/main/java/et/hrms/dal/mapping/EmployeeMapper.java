package et.hrms.dal.mapping;


import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "employeeId", source = "id")
    EmployeeDTO toEmployeeDTO(Employee employee);


    @Mapping(target = "id", source = "employeeId")
    Employee toEmployee(EmployeeDTO employeeDTO);

    List<Employee> getListOfEmployee(List<EmployeeDTO> employeeDTOS);


}
