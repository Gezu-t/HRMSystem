package et.hrms.controller.impl;


import et.hrms.controller.EducationController;
import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.model.Education;
import et.hrms.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/educations")
public class EducationControllerImpl implements EducationController {


    private final EducationService educationService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Education createEducation(@RequestBody EducationDTO educationDTO) {

        return educationService.createEducation(educationDTO);
    }


    @GetMapping("getAll")
    public List<EducationDTO> getAllEducation() {


        return educationService.getAllEducationList();

    }
}
