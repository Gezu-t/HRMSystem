package et.hrms.controller.impl;


import et.hrms.controller.EducationController;
import et.hrms.dal.dto.EducationDTO;
import et.hrms.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/educations")
public class EducationControllerImpl implements EducationController {


    private final EducationService educationService;


    @Override
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEducation(@Valid @RequestBody EducationDTO educationDTO) {

        educationService.createEducation(educationDTO);
    }


    @Override
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EducationDTO> getAllEducation(@RequestParam("page") int page, @RequestParam("size") int size) {

        return educationService.getAllEducationList(page, size);

    }
}
