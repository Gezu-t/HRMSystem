package et.hrms.controller;

import et.hrms.dal.dto.EducationDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EducationController {

  void createEducation(@RequestBody EducationDTO educationDTO);


  List<EducationDTO> getAllEducation(@RequestParam("page") int page, @RequestParam("size") int size);
}
