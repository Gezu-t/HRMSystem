package com.hrms.employee.core.controller.employee.imp;


import com.hrms.employee.core.controller.employee.FamilyController;
import com.hrms.employee.core.exceptions.EntityNotFoundException;
import com.hrms.employee.core.dal.dto.employee.FamilyDTO;
import com.hrms.employee.core.service.employee.FamilyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/families")
public class FamilyControllerImpl implements FamilyController {

  private final FamilyService familyService;

  @Override
  @PostMapping("/{employeeId}")
  public ResponseEntity<FamilyDTO> createFamily(@Valid @PathVariable long employeeId, @RequestBody FamilyDTO familyDTO) {

    FamilyDTO famDTO = familyService.createEmployeeFamily(employeeId, familyDTO);

    return new ResponseEntity<>(famDTO, HttpStatus.CREATED);
  }

  @Override
  @GetMapping(value = "/{familyId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public FamilyDTO getFamilyById(@PathVariable long familyId) {


    return familyService.getFamilyById(familyId);
  }


  @Override
  @GetMapping
  public ResponseEntity<List<FamilyDTO>> getAllFamilies(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size,
          @RequestParam(defaultValue = "id, Asc") String[] sort) {
    Sort sortOrder = Sort.by(sort[0]);
    if (sort.length > 1) {
      sortOrder = sort[1].equalsIgnoreCase("desc") ? sortOrder.descending() : sortOrder.ascending();
    }
    try {
      List<FamilyDTO> familyDTOS = familyService.getAllFamily(page, size, sortOrder);
      return new ResponseEntity<>(familyDTOS, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }


}
