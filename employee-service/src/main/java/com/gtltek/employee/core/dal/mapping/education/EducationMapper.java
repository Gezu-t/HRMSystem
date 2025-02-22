package com.gtltek.employee.core.dal.mapping.education;


import com.gtltek.employee.core.dal.dto.education.EducationDTO;
import com.gtltek.employee.core.dal.dto.education.EducationLevelDTO;
import com.gtltek.employee.core.dal.model.education.Education;
import com.gtltek.employee.core.dal.model.education.EducationLevel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    EducationDTO toEducationDTO(Education education);

    Education toEducation(EducationDTO educationDTO);

    EducationLevelDTO toEducationLevelDTO(EducationLevel educationLevel);

    EducationLevel toEducationLevel(EducationLevelDTO educationLevelDTO);

}
