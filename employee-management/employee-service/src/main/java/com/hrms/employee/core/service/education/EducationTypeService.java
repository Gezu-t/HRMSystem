package com.hrms.employee.core.service.education;

import com.hrms.employee.core.dal.dto.education.EducationTypeDTO;

import java.util.List;

public interface EducationTypeService {
  void createEducationType(EducationTypeDTO educationTypeDTO);

  EducationTypeDTO updateEducationType(EducationTypeDTO educationTypeDTO);

  EducationTypeDTO getEducationTypeById(Long educationTypeId);

  List<EducationTypeDTO> getAllEducationTypes();
}
