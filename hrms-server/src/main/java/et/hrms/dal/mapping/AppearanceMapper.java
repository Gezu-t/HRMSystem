package et.hrms.dal.mapping;


import et.hrms.dal.dto.AppearanceDTO;
import et.hrms.dal.model.Appearance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AppearanceMapper {

    AppearanceMapper INSTANCE = Mappers.getMapper(AppearanceMapper.class);


    Appearance toAppearance(AppearanceDTO appearanceDTO);

    AppearanceDTO toAppearanceDTO(Appearance appearance);


}
