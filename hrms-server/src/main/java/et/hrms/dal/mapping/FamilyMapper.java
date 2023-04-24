package et.hrms.dal.mapping;


import et.hrms.dal.dto.employee.FamilyDTO;
import et.hrms.dal.model.employee.Family;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FamilyMapper {

    FamilyMapper INSTANCE = Mappers.getMapper(FamilyMapper.class);

    @Mapping(target = "familyId", source = "id")
    @Mapping(target = "employeeId", source = "employee.id")
    FamilyDTO toFamilyDTO(Family family);

    @Mapping(target = "id", source = "familyId")
    @Mapping(target = "employee.id", source = "employeeId")
    Family toFamily(FamilyDTO familyDTO);



}
