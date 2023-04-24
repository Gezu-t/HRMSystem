package et.hrms.dal.mapping.education;


import et.hrms.dal.dto.education.EducationAwardDTO;
import et.hrms.dal.dto.education.EducationDTO;
import et.hrms.dal.dto.education.EducationLevelDTO;
import et.hrms.dal.model.education.Education;
import et.hrms.dal.model.education.EducationAward;
import et.hrms.dal.model.education.EducationLevel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    EducationDTO toEducationDTO(Education education);

    Education toEducation(EducationDTO educationDTO);

    EducationLevelDTO toEducationLevelDTO(EducationLevel educationLevel);
    EducationLevel toEducationLevel(EducationLevelDTO educationLevelDTO);



    EducationAwardDTO toEducationAwardDTO(EducationAward educationAward);
    EducationAward toEducationAward(EducationAwardDTO educationAwardDTO);

}
