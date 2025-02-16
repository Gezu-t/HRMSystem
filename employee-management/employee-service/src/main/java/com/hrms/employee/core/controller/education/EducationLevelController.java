package com.hrms.employee.core.controller.education;

import com.hrms.employee.core.dal.dto.education.EducationLevelDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EducationLevelController {

  @PostMapping
  ResponseEntity<Void> createEducationLevel(@Valid @RequestBody EducationLevelDTO educationLevelDTO);

  @PutMapping("/{id}")
  ResponseEntity<EducationLevelDTO> updateEducationLevel(@PathVariable("id") Long id, @Valid @RequestBody EducationLevelDTO educationLevelDTO);

  @GetMapping("/{id}")
  ResponseEntity<EducationLevelDTO> getEducationLevelById(@PathVariable("id") Long id);

  @GetMapping
  ResponseEntity<List<EducationLevelDTO>> getAllEducationLevels();
}
