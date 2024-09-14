package com.hrmsystem.employeeservice.core.controller.education.impl;

import com.hrmsystem.employeeservice.core.controller.education.EducationTypeController;
import com.hrmsystem.employeeservice.core.dal.dto.education.EducationTypeDTO;
import com.hrmsystem.employeeservice.core.service.education.EducationTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/educationTypes")
public class EducationTypeControllerImpl implements EducationTypeController {

  private final EducationTypeService educationTypeService;


  @Override
  @PostMapping
  public ResponseEntity<Void> createEducationType(@Valid @RequestBody EducationTypeDTO educationTypeDTO) {
    educationTypeService.createEducationType(educationTypeDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<EducationTypeDTO> updateEducationType(@PathVariable("id") Long educationTypeId,
                                                              @Valid @RequestBody EducationTypeDTO educationTypeDTO) {
    educationTypeDTO.setEducationTypeId(educationTypeId);
    EducationTypeDTO updatedEducationType = educationTypeService.updateEducationType(educationTypeDTO);
    return ResponseEntity.ok(updatedEducationType);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<EducationTypeDTO> getEducationTypeById(@PathVariable("id") Long educationTypeId) {
    EducationTypeDTO educationTypeDTO = educationTypeService.getEducationTypeById(educationTypeId);
    return ResponseEntity.ok(educationTypeDTO);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<EducationTypeDTO>> getAllEducationTypes() {
    List<EducationTypeDTO> educationTypeDTOs = educationTypeService.getAllEducationTypes();
    return ResponseEntity.ok(educationTypeDTOs);
  }
}
