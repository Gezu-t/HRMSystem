package com.hrms.employee.core.controller.education.impl;

import com.hrms.employee.core.controller.education.EducationLevelController;
import com.hrms.employee.core.dal.dto.education.EducationLevelDTO;
import com.hrms.employee.core.service.education.EducationLevelService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/educationLevels")
@Validated
public class EducationLevelControllerImpl implements EducationLevelController {

  private static final Logger logger = LoggerFactory.getLogger(EducationLevelControllerImpl.class);
  private final EducationLevelService educationLevelService;

    public EducationLevelControllerImpl(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    @Override
  @PostMapping
  public ResponseEntity<Void> createEducationLevel(@Valid @RequestBody EducationLevelDTO educationLevelDTO) {
    logger.info("Received request to create education level: {}", educationLevelDTO);
    educationLevelService.createEducationLevel(educationLevelDTO);
    logger.info("Successfully created education level: {}", educationLevelDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<EducationLevelDTO> updateEducationLevel(@PathVariable("id") Long id, @Valid @RequestBody EducationLevelDTO educationLevelDTO) {
    logger.info("Received request to update education level with ID {}: {}", id, educationLevelDTO);
    educationLevelDTO.setEducationLevelId(id);
    EducationLevelDTO updatedEducationLevel = educationLevelService.updateEducationLevel(educationLevelDTO);
    logger.info("Successfully updated education level with ID {}: {}", id, updatedEducationLevel);
    return ResponseEntity.ok(updatedEducationLevel);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<EducationLevelDTO> getEducationLevelById(@PathVariable("id") Long id) {
    logger.info("Received request to fetch education level with ID: {}", id);
    EducationLevelDTO educationLevelDTO = educationLevelService.findEducationLevelById(id);
    logger.info("Successfully fetched education level with ID: {}", id);
    return ResponseEntity.ok(educationLevelDTO);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<EducationLevelDTO>> getAllEducationLevels() {
    logger.info("Received request to fetch all education levels");
    List<EducationLevelDTO> educationLevelDTOList = educationLevelService.getAllEducationLevels();
    logger.info("Successfully fetched all education levels");
    return ResponseEntity.ok(educationLevelDTOList);
  }

}
