package et.hrms.service;

import et.hrms.dal.dto.EducationDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EducationService {
    void createEducation(EducationDTO educationDTO);


    EducationDTO updateEducationInfo(EducationDTO educationDTO);

     EducationDTO getEducationByInstitution(String name);


    List<EducationDTO> getAllEducationList(int page, int size, Sort sort);
}
