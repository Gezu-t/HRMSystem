package com.hrmsystem.employeeservice.core.dal.mapping.education;

import dal.dto.education.EducationDetailDTO;
import dal.dto.education.EducationTypeDTO;
import dal.model.education.EducationDetail;
import dal.model.education.EducationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EducationDetailMapper {


  EducationDetailMapper INSTANCE = Mappers.getMapper(EducationDetailMapper.class);

  EducationDetailDTO toEducationDetailDTO(EducationDetail educationDetail);
  EducationDetail toEducationDetail(EducationDetailDTO educationDetailDTO);

  EducationTypeDTO toEducationTypeDTO(EducationType educationType);
  EducationType toEducationType(EducationTypeDTO educationTypeDTO);

}
