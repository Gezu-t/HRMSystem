package com.hrmsystem.employeeservice.core.controller.employee;

import com.hrmsystem.employeeservice.core.dal.dto.employee.FamilyDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FamilyController {

    ResponseEntity<FamilyDTO> createFamily(@Valid @PathVariable long employeeId, @RequestBody FamilyDTO familyDTO);
    FamilyDTO getFamilyById(@PathVariable long familyId);
    @GetMapping
    ResponseEntity<List<FamilyDTO>> getAllFamilies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id, Asc") String[] sort);
}
