package et.hrms.dal.mapping;


import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface EmployeeMapper {


    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);


//    @Mapping(target = "employeeNo", source = "employeeNumber")
//    @Mapping(target = "firstName", source = "firstName")
//    @Mapping(target = "lastName", source = "lastName")
//    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
//    @Mapping(target = "dateOfJoining", source = "dateOfJoining")
    EmployeeDTO toEmployeeDTO(Employee employee);


//    @Mapping(target = "employeeNumber", source = "employeeNo")
//    @Mapping(target = "firstName", source = "firstName")
//    @Mapping(target = "lastName", source = "lastName")
//    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
//    @Mapping(target = "dateOfJoining", source = "dateOfJoining")
    Employee toEmployee(EmployeeDTO employeeDTO);
//
//    List<EmployeeDTO> toEmployeeDTO(List<Employee> employees);

//
    List<Employee> getListOfEmployee(List<EmployeeDTO> employeeDTOS);
//    List<EmployeeDTO> getListOfEmployeeDTO(List<Employee> employees);


}
