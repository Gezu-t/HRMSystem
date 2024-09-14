package com.hrmsystem.employeeservice.core.service.education;

import com.hrmsystem.employeeservice.core.dal.dto.education.EducationDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EducationService {
    void createEducation(Long employeeId, EducationDTO educationDTO);


    EducationDTO updateEducationInfo(Long employeeId, EducationDTO educationDTO);

     EducationDTO getEducationByInstitution(String name);


    List<EducationDTO> getAllEducationList(int page, int size, Sort sort);
}
