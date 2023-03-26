package et.hrms.controller;

import et.hrms.dal.dto.EducationDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EducationController {

    @PostMapping
    ResponseEntity<Void> createEducation(@Valid @RequestBody EducationDTO educationDTO);

    @PutMapping
    ResponseEntity<EducationDTO> updateEducationInfo(@Valid @RequestBody EducationDTO educationDTO);

    @GetMapping("/institution/{name}")
    ResponseEntity<EducationDTO> getEducationByInstitution(@PathVariable String name);

    @GetMapping
    ResponseEntity<List<EducationDTO>> getAllEducationList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sort", required = false) Sort sort);
}
