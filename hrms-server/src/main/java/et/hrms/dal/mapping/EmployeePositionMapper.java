package et.hrms.dal.mapping;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EmployeePositionMapper {

    EmployeePositionMapper INSTANCE = Mappers.getMapper(EmployeePositionMapper.class);

}
