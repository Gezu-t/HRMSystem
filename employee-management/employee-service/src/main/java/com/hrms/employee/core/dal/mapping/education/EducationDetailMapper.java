package com.hrms.employee.core.dal.mapping.education;

import com.hrms.employee.core.dal.dto.education.EducationDetailDTO;
import com.hrms.employee.core.dal.dto.education.EducationTypeDTO;
import com.hrms.employee.core.dal.model.education.EducationDetail;
import com.hrms.employee.core.dal.model.education.EducationType;
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
