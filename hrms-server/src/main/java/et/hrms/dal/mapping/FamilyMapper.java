package et.hrms.dal.mapping;


import et.hrms.dal.dto.FamilyDTO;
import et.hrms.dal.model.Family;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FamilyMapper {

    FamilyMapper INSTANCE = Mappers.getMapper(FamilyMapper.class);

    FamilyDTO toFamilyDTO(Family family);
    Family toFamily(FamilyDTO familyDTO);



}
