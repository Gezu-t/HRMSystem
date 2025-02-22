package com.gtltek.employee.core.service.education;

import com.gtltek.employee.core.dal.dto.education.EducationDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EducationService {
    void createEducation(Long employeeId, EducationDTO educationDTO);


    EducationDTO updateEducationInfo(Long employeeId, EducationDTO educationDTO);

     EducationDTO getEducationByInstitution(String name);


    List<EducationDTO> getAllEducationList(int page, int size, Sort sort);
}
