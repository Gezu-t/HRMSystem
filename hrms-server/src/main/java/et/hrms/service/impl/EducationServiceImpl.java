package et.hrms.service.impl;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.model.Education;
import et.hrms.dal.repository.EducationRepository;
import et.hrms.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {



    private final EducationRepository educationRepository;



    @Override
    public Education createEducation(EducationDTO educationDTO) {

        Education education = new Education();
        education.setEducationInstitution(educationDTO.getEducationInstitutionName());
        education.setEducationLevel(educationDTO.getEducationLevel());
        education.setEducationMajor(educationDTO.getEducationMajor());
        education.setEducationMinor(educationDTO.getEducationMinor());
        education.setEducationType(educationDTO.getEducationType());
        educationRepository.save(education);

        return education;
    }



    @Override
    public List<EducationDTO> getAllEducationList() {
        List<EducationDTO> educationDTOS = new ArrayList<>();
        List<Education> educations = educationRepository.findAll();
        EducationDTO educationDTO = new EducationDTO();
        for (Education education : educations) {
            educationDTO.setEducationId(education.getId());
            educationDTO.setEducationMinor(education.getEducationMajor());
            educationDTO.setEducationMinor(education.getEducationMinor());
            educationDTO.setEducationLevel(education.getEducationLevel());
            educationDTOS.add(educationDTO);
        }
        return educationDTOS;
    }



}
