package et.hrms.service;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.model.Education;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface EducationService {
    void createEducation(EducationDTO educationDTO);


    EducationDTO updateEducationInfo(EducationDTO educationDTO) throws Exception;

     EducationDTO getEducationByInstitution(String name);

    @GetExchange
    List<EducationDTO> getAllEducationList(int page, int size);
}
