package com.hrmsystem.employeeservice.core.dal.mapping.education;

import dal.dto.education.EducationAwardDTO;
import dal.dto.education.EducationDTO;
import dal.dto.education.EducationLevelDTO;
import dal.model.education.Education;
import dal.model.education.EducationAward;
import dal.model.education.EducationLevel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EducationMapperImpl implements EducationMapper {

    @Override
    public EducationDTO toEducationDTO(Education education) {
        if ( education == null ) {
            return null;
        }

        EducationDTO educationDTO = new EducationDTO();

        educationDTO.setCompletionDate( education.getCompletionDate() );
        educationDTO.setStartDate( education.getStartDate() );
        educationDTO.setEndDate( education.getEndDate() );
        educationDTO.setInstitution( education.getInstitution() );

        return educationDTO;
    }

    @Override
    public Education toEducation(EducationDTO educationDTO) {
        if ( educationDTO == null ) {
            return null;
        }

        Education education = new Education();

        education.setCompletionDate( educationDTO.getCompletionDate() );
        education.setStartDate( educationDTO.getStartDate() );
        education.setEndDate( educationDTO.getEndDate() );
        education.setInstitution( educationDTO.getInstitution() );

        return education;
    }

    @Override
    public EducationLevelDTO toEducationLevelDTO(EducationLevel educationLevel) {
        if ( educationLevel == null ) {
            return null;
        }

        EducationLevelDTO educationLevelDTO = new EducationLevelDTO();

        educationLevelDTO.setEducationLevelName( educationLevel.getEducationLevelName() );

        return educationLevelDTO;
    }

    @Override
    public EducationLevel toEducationLevel(EducationLevelDTO educationLevelDTO) {
        if ( educationLevelDTO == null ) {
            return null;
        }

        EducationLevel educationLevel = new EducationLevel();

        educationLevel.setEducationLevelName( educationLevelDTO.getEducationLevelName() );

        return educationLevel;
    }

    @Override
    public EducationAwardDTO toEducationAwardDTO(EducationAward educationAward) {
        if ( educationAward == null ) {
            return null;
        }

        EducationAwardDTO educationAwardDTO = new EducationAwardDTO();

        return educationAwardDTO;
    }

    @Override
    public EducationAward toEducationAward(EducationAwardDTO educationAwardDTO) {
        if ( educationAwardDTO == null ) {
            return null;
        }

        EducationAward educationAward = new EducationAward();

        return educationAward;
    }
}
