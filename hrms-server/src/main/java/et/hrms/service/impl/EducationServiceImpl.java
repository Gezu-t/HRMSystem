package et.hrms.service.impl;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.mapping.EducationMapper;
import et.hrms.dal.model.Education;
import et.hrms.dal.repository.EducationRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.EducationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;

    @Transactional
    @Override
    public void createEducation(EducationDTO educationDTO) {
        validateEducationDTO(educationDTO);
        Education education = educationMapper.toEducation(educationDTO);
        education.setCreatedAt(LocalDateTime.now());
        educationRepository.save(education);
    }

    @Transactional
    @Override
    public EducationDTO updateEducationInfo(EducationDTO educationDTO) {
        validateEducationDTO(educationDTO);
        Education education = educationRepository.findById(educationDTO.getEducationId())
                .orElseThrow(() -> new EntityNotFoundException("Education information is not found by this id: " + educationDTO.getEducationId()));

        updateEducationFields(education, educationDTO);
        education.setUpdatedAt(LocalDateTime.now());

        Education updatedEducation = educationRepository.save(education);
        return educationMapper.toEducationDTO(updatedEducation);
    }

    private void validateEducationDTO(EducationDTO educationDTO) {
        Objects.requireNonNull(educationDTO.getInstitution(), "Institution name must not be null");
        Objects.requireNonNull(educationDTO.getDegree(), "Degree must not be null");
        if (!(educationDTO.getEducationStartDate() == null || educationDTO.getEducationEndDate() == null)) {
            throw new IllegalArgumentException("Education start date must be before education end date");

        }
    }

    private void updateEducationFields(Education education, EducationDTO educationDTO) {
        BeanUtils.copyProperties(educationDTO, education, "id", "createdAt", "updatedAt");
    }

    @Override
    public EducationDTO getEducationByInstitution(String name) {
        Education education = educationRepository.findByInstitution(name);
        return educationMapper.toEducationDTO(education);
    }

    @Override
    public List<EducationDTO> getAllEducationList(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Education> educations = educationRepository.findAll(pageable);
        List<EducationDTO> educationDTOs = educations.stream()
                .map(educationMapper::toEducationDTO)
                .toList();
        return new ArrayList<>(educationDTOs);
    }

}
