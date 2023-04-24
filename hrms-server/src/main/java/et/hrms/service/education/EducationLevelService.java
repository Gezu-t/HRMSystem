package et.hrms.service.education;

import et.hrms.dal.dto.education.EducationLevelDTO;

import java.util.List;

public interface EducationLevelService {
  void createEducationLevel(EducationLevelDTO educationLevelDTO);

  EducationLevelDTO updateEducationLevel(EducationLevelDTO educationLevelDTO);

  EducationLevelDTO findEducationLevelById(Long educationLevelId);

  List<EducationLevelDTO> getAllEducationLevel();
}
