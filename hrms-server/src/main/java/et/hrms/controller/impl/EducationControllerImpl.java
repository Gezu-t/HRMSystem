package et.hrms.controller.impl;


import et.hrms.controller.EducationController;
import et.hrms.dal.dto.EducationDTO;
import et.hrms.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/educations")
public class EducationControllerImpl implements EducationController {


    private final EducationService educationService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEducation(@RequestBody EducationDTO educationDTO) {

        educationService.createEducation(educationDTO);
    }


    @GetMapping("all")
    public List<EducationDTO> getAllEducation(@RequestParam("page") int page, @RequestParam("size") int size) {

        return educationService.getAllEducationList(page, size);

    }
}
