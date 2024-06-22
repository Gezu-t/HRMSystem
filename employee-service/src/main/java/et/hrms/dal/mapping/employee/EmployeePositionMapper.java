package et.hrms.dal.mapping.employee;


import et.hrms.dal.dto.employee.EmployeePositionDTO;
import et.hrms.dal.model.employee.EmployeePosition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeePositionMapper {

    EmployeePositionMapper INSTANCE = Mappers.getMapper(EmployeePositionMapper.class);

    EmployeePosition toEmployeePosition(EmployeePositionDTO employeePositionDTO);
    EmployeePositionDTO toEmployeePositionDTO(EmployeePosition employeePosition);

}
