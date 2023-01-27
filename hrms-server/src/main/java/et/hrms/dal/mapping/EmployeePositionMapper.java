package et.hrms.dal.mapping;


import et.hrms.dal.dto.EmployeePositionDTO;
import et.hrms.dal.model.EmployeePosition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeePositionMapper {

    EmployeePositionMapper INSTANCE = Mappers.getMapper(EmployeePositionMapper.class);

    EmployeePosition toEmployeePosition(EmployeePositionDTO employeePositionDTO);
    EmployeePositionDTO toEmployeePositionDTO(EmployeePosition employeePosition);

}
