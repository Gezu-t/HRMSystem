package com.gtltek.employee.core.controller.education;

import com.gtltek.employee.core.dal.dto.education.EducationTypeDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EducationTypeController {
  @PostMapping
  ResponseEntity<Void> createEducationType(@Valid @RequestBody EducationTypeDTO educationTypeDTO);

  @PutMapping("/{id}")
  ResponseEntity<EducationTypeDTO> updateEducationType(@PathVariable("id") Long educationTypeId,
                                                       @Valid @RequestBody EducationTypeDTO educationTypeDTO);

  @GetMapping("/{id}")
  ResponseEntity<EducationTypeDTO> getEducationTypeById(@PathVariable("id") Long educationTypeId);

  @GetMapping
  ResponseEntity<List<EducationTypeDTO>> getAllEducationTypes();
}
