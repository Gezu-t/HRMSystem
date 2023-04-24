package et.hrms.controller.education;

import et.hrms.dal.dto.education.EducationLevelDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EducationLevelController {
  ResponseEntity<Void> createEducationLevel(@RequestBody EducationLevelDTO educationLevelDTO);

  ResponseEntity<EducationLevelDTO> updateEducationLevel(@PathVariable("id") Long id, @RequestBody EducationLevelDTO educationLevelDTO);

  ResponseEntity<EducationLevelDTO> getEducationLevelById(@PathVariable("id") Long id);

  ResponseEntity<List<EducationLevelDTO>> getAllEducationLevels();
}
