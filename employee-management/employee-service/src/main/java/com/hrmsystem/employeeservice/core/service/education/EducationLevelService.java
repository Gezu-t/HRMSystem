package com.hrmsystem.employeeservice.core.service.education;

import com.hrmsystem.employeeservice.core.dal.dto.education.EducationLevelDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface EducationLevelService {

  void createEducationLevel(@Valid EducationLevelDTO educationLevelDTO);

  EducationLevelDTO updateEducationLevel(@Valid EducationLevelDTO educationLevelDTO);

  EducationLevelDTO findEducationLevelById(Long educationLevelId);

  List<EducationLevelDTO> getAllEducationLevels();
}
