package com.hrmsystem.employeeservice.core.dal.mapping.education;

import dal.dto.education.EducationDetailDTO;
import dal.dto.education.EducationTypeDTO;
import dal.model.education.EducationDetail;
import dal.model.education.EducationType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EducationDetailMapperImpl implements EducationDetailMapper {

    @Override
    public EducationDetailDTO toEducationDetailDTO(EducationDetail educationDetail) {
        if ( educationDetail == null ) {
            return null;
        }

        EducationDetailDTO educationDetailDTO = new EducationDetailDTO();

        educationDetailDTO.setDegree( educationDetail.getDegree() );
        educationDetailDTO.setMajor( educationDetail.getMajor() );
        educationDetailDTO.setMinor( educationDetail.getMinor() );
        educationDetailDTO.setGpa( educationDetail.getGpa() );
        educationDetailDTO.setGpaOutOf( educationDetail.getGpaOutOf() );
        educationDetailDTO.setGpaOutOfScale( educationDetail.getGpaOutOfScale() );
        educationDetailDTO.setAcademicYear( educationDetail.getAcademicYear() );

        return educationDetailDTO;
    }

    @Override
    public EducationDetail toEducationDetail(EducationDetailDTO educationDetailDTO) {
        if ( educationDetailDTO == null ) {
            return null;
        }

        EducationDetail educationDetail = new EducationDetail();

        educationDetail.setId( educationDetailDTO.getEducationTypeId() );
        educationDetail.setDegree( educationDetailDTO.getDegree() );
        educationDetail.setMajor( educationDetailDTO.getMajor() );
        educationDetail.setMinor( educationDetailDTO.getMinor() );
        educationDetail.setGpa( educationDetailDTO.getGpa() );
        educationDetail.setGpaOutOf( educationDetailDTO.getGpaOutOf() );
        educationDetail.setGpaOutOfScale( educationDetailDTO.getGpaOutOfScale() );
        educationDetail.setAcademicYear( educationDetailDTO.getAcademicYear() );

        return educationDetail;
    }

    @Override
    public EducationTypeDTO toEducationTypeDTO(EducationType educationType) {
        if ( educationType == null ) {
            return null;
        }

        EducationTypeDTO educationTypeDTO = new EducationTypeDTO();

        if ( educationType.getEducationTypeId() != null ) {
            educationTypeDTO.setEducationTypeId( Long.parseLong( educationType.getEducationTypeId() ) );
        }
        educationTypeDTO.setName( educationType.getName() );

        return educationTypeDTO;
    }

    @Override
    public EducationType toEducationType(EducationTypeDTO educationTypeDTO) {
        if ( educationTypeDTO == null ) {
            return null;
        }

        EducationType educationType = new EducationType();

        if ( educationTypeDTO.getEducationTypeId() != null ) {
            educationType.setEducationTypeId( String.valueOf( educationTypeDTO.getEducationTypeId() ) );
        }
        educationType.setName( educationTypeDTO.getName() );

        return educationType;
    }
}
