package et.hrms.controller.education;

import et.hrms.dal.dto.education.EducationDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EducationController {


  ResponseEntity<Void> createEducation(@Valid @PathVariable("employeeId") Long employeeId, @RequestBody EducationDTO educationDTO);
  ResponseEntity<EducationDTO> updateEducationInfo(@Valid @PathVariable("employeeId") Long employeeId, @RequestBody EducationDTO educationDTO);
  ResponseEntity<EducationDTO> getEducationByInstitution(@PathVariable String name);
  ResponseEntity<List<EducationDTO>> getAllEducationList(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                         @RequestParam(defaultValue = "institution") String sortBy);
}
