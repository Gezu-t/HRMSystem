package et.hrms.dal.mapping;


import et.hrms.dal.dto.EmployeeAppearanceDTO;
import et.hrms.dal.model.EmployeeAppearance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeAppearanceMapper {

    EmployeeAppearanceMapper INSTANCE = Mappers.getMapper(EmployeeAppearanceMapper.class);


    @Mapping(target = "employee.id", source = "employeeId")
    EmployeeAppearance toAppearance(EmployeeAppearanceDTO employeeAppearanceDTO);

    @Mapping(target = "employeeId", source = "employee.id")
    EmployeeAppearanceDTO toAppearanceDTO(EmployeeAppearance employeeAppearance);


}
