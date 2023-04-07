package et.hrms.controller.impl;


import et.hrms.controller.EducationController;
import et.hrms.dal.dto.EducationDTO;
import et.hrms.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationControllerImpl implements EducationController {
    private final EducationService educationService;

    @Override
    @PostMapping
    public ResponseEntity<Void> createEducation(@Valid @RequestBody EducationDTO educationDTO) {
        educationService.createEducation(educationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @PutMapping
    public ResponseEntity<EducationDTO> updateEducationInfo(@Valid @RequestBody EducationDTO educationDTO) {
        EducationDTO updatedEducationDTO = educationService.updateEducationInfo(educationDTO);
        return ResponseEntity.ok(updatedEducationDTO);
    }

    @Override
    @GetMapping("/{name}")
    public ResponseEntity<EducationDTO> getEducationByInstitution(@PathVariable String name) {
        EducationDTO educationDTO = educationService.getEducationByInstitution(name);
        if (educationDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(educationDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EducationDTO>> getAllEducationList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sort", required = false) Sort sort) {
        List<EducationDTO> educationDTOs = educationService.getAllEducationList(page, size, sort);
        return ResponseEntity.ok(educationDTOs);
    }
}
