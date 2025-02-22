package com.gtltek.employee.core.service.education;

import com.gtltek.employee.core.dal.dto.education.EducationTypeDTO;

import java.util.List;

public interface EducationTypeService {
  void createEducationType(EducationTypeDTO educationTypeDTO);

  EducationTypeDTO updateEducationType(EducationTypeDTO educationTypeDTO);

  EducationTypeDTO getEducationTypeById(Long educationTypeId);

  List<EducationTypeDTO> getAllEducationTypes();
}
