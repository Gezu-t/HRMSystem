package com.hrmsystem.employeeprofileservice.dal.mapping.education;


import com.hrmsystem.employeeprofileservice.dal.dto.education.EducationAwardDTO;
import com.hrmsystem.employeeprofileservice.dal.dto.education.EducationDTO;
import com.hrmsystem.employeeprofileservice.dal.dto.education.EducationLevelDTO;
import com.hrmsystem.employeeservice.core.dal.model.education.Education;
import com.hrmsystem.employeeservice.core.dal.model.education.EducationAward;
import com.hrmsystem.employeeservice.core.dal.model.education.EducationLevel;
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
