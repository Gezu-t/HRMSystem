package et.hrms.controller.education;

import et.hrms.dal.dto.education.EducationLevelDTO;
import et.hrms.service.education.EducationLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/educationLevels")
public class EducationLevelControllerImpl implements EducationLevelController{

  private final EducationLevelService educationLevelService;


  @Override
  @PostMapping
  public ResponseEntity<Void> createEducationLevel(@RequestBody EducationLevelDTO educationLevelDTO) {
    educationLevelService.createEducationLevel(educationLevelDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<EducationLevelDTO> updateEducationLevel(@PathVariable("id") Long id, @RequestBody EducationLevelDTO educationLevelDTO) {
    educationLevelDTO.setEducationLevelId(id);
    EducationLevelDTO updatedEducationLevel = educationLevelService.updateEducationLevel(educationLevelDTO);
    return ResponseEntity.ok(updatedEducationLevel);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<EducationLevelDTO> getEducationLevelById(@PathVariable("id") Long id) {
    EducationLevelDTO educationLevelDTO = educationLevelService.findEducationLevelById(id);
    return ResponseEntity.ok(educationLevelDTO);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<EducationLevelDTO>> getAllEducationLevels() {
    List<EducationLevelDTO> educationLevelDTOList = educationLevelService.getAllEducationLevel();
    return ResponseEntity.ok(educationLevelDTOList);
  }


}
