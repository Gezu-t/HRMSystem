package et.hrms.dal.mapping;


import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.dal.model.employee.Employee;
import et.hrms.dal.model.structure.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO toEmployeeDTO(Employee employee);


    Employee toEmployee(EmployeeDTO employeeDTO);

    List<Employee> getListOfEmployee(List<EmployeeDTO> employeeDTOS);

    @Mapping(source = "organizationName", target = "orgName")
    EmployeeDTO toEmployeeOrganizatin(Organization organization);


}
