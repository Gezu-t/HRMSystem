package et.hrms.service;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.model.Education;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface EducationService {
    Education createEducation(EducationDTO educationDTO);


    @GetExchange
    List<EducationDTO> getAllEducationList();
}
