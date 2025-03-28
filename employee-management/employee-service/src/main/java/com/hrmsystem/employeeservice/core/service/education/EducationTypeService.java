package com.hrmsystem.employeeservice.core.service.education;

import dal.dto.education.EducationTypeDTO;

import java.util.List;

public interface EducationTypeService {
  void createEducationType(EducationTypeDTO educationTypeDTO);

  EducationTypeDTO updateEducationType(EducationTypeDTO educationTypeDTO);

  EducationTypeDTO getEducationTypeById(Long educationTypeId);

  List<EducationTypeDTO> getAllEducationTypes();
}
