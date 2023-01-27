package et.hrms.service.impl;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.mapping.EducationMapper;
import et.hrms.dal.model.Education;
import et.hrms.dal.repository.EducationRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {



    private final EducationRepository educationRepository;

    private final EducationMapper educationMapper;



    @Override
    public void createEducation(EducationDTO educationDTO) {

        Education education = educationMapper.toEducation(educationDTO);
        education.setCreatedAt(LocalDateTime.now());
        educationRepository.save(education);

    }


    @Override
    public EducationDTO updateEducationInfo(EducationDTO educationDTO) {
// retrieve the existing education record from the database
        Education education = educationRepository.findById(educationDTO.getEducationId())
                .orElseThrow(() -> new EntityNotFoundException("Education information is not found by this id: " + educationDTO.getEducationId()));

        // update the fields of the education record with the new values from the DTO
        education.setInstitution(educationDTO.getInstitution());
        education.setEducationMajor(educationDTO.getEducationMajor());
        education.setEducationMinor(educationDTO.getEducationMinor());
        education.setEducationLevel(educationDTO.getEducationLevel());
        education.setAward(educationDTO.getAward());
        education.setAwardDate(educationDTO.getAwardDate());
        education.setEducationStartDate(educationDTO.getEducationStartDate());
        education.setEducationEndDate(educationDTO.getEducationEndDate());

        // add updated date for current information
        education.setUpdatedAt(LocalDateTime.now());

        // save the updated education record to the database
        education = educationRepository.save(education);

        // map the updated education record to a DTO and return it
        return educationMapper.toEducationDTO(education);
    }


    @Override
    public EducationDTO getEducationByInstitution(String name){
        Education education = educationRepository.findByInstitution(name);
        return educationMapper.toEducationDTO(education);
    }


    @Override
    public List<EducationDTO> getAllEducationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Education> educations = educationRepository.findAll(pageable);
        return educations.stream()
                .map(educationMapper::toEducationDTO)
                .toList();
    }



}
