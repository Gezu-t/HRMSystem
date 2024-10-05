package com.hrmsystem.employeeservice.core.dal.mapping.education;


import dal.dto.education.EducationAwardDTO;
import dal.dto.education.EducationDTO;
import dal.dto.education.EducationLevelDTO;
import dal.model.education.Education;
import dal.model.education.EducationAward;
import dal.model.education.EducationLevel;
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
