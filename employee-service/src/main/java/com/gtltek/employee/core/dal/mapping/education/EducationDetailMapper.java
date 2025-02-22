package com.gtltek.employee.core.dal.mapping.education;

import com.gtltek.employee.core.dal.dto.education.EducationDetailDTO;
import com.gtltek.employee.core.dal.dto.education.EducationTypeDTO;
import com.gtltek.employee.core.dal.model.education.EducationDetail;
import com.gtltek.employee.core.dal.model.education.EducationType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EducationDetailMapper {


  EducationDetailMapper INSTANCE = Mappers.getMapper(EducationDetailMapper.class);

  EducationDetailDTO toEducationDetailDTO(EducationDetail educationDetail);
  EducationDetail toEducationDetail(EducationDetailDTO educationDetailDTO);

  EducationTypeDTO toEducationTypeDTO(EducationType educationType);
  EducationType toEducationType(EducationTypeDTO educationTypeDTO);

}
