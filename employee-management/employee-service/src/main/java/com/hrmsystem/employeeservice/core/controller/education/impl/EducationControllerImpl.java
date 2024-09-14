package com.hrmsystem.employeeservice.core.controller.education.impl;


import com.hrmsystem.employeeservice.core.controller.education.EducationController;
import com.hrmsystem.employeeservice.core.dal.dto.education.EducationDTO;
import com.hrmsystem.employeeservice.core.service.education.EducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationControllerImpl implements EducationController {

    private static final Logger log = LoggerFactory.getLogger(EducationControllerImpl.class);
    private final EducationService educationService;

    public EducationControllerImpl(EducationService educationService) {
        this.educationService = educationService;
    }

    @Override
    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<Void> createEducation(@PathVariable Long employeeId, @RequestBody EducationDTO educationDTO) {
        log.info("Creating education for employee with ID: {}", employeeId);
        educationService.createEducation(employeeId, educationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<EducationDTO> updateEducationInfo(@PathVariable Long employeeId, @RequestBody EducationDTO educationDTO) {
        log.info("Updating education for employee with ID: {}", employeeId);
        EducationDTO updatedEducation = educationService.updateEducationInfo(employeeId, educationDTO);
        return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
    }

    @Override
    @GetMapping("/institution/{name}")
    public ResponseEntity<EducationDTO> getEducationByInstitution(@PathVariable String name) {
        log.info("Fetching education by institution name: {}", name);
        EducationDTO education = educationService.getEducationByInstitution(name);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EducationDTO>> getAllEducationList(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                                  @RequestParam(defaultValue = "institution") String sortBy) {
        log.info("Fetching all education records");
        List<EducationDTO> educations = educationService.getAllEducationList(page, size, Sort.by(direction, sortBy));
        return new ResponseEntity<>(educations, HttpStatus.OK);
    }
}
