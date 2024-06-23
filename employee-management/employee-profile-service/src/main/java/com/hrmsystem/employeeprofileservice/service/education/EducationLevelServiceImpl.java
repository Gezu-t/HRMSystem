package com.hrmsystem.employeeprofileservice.service.education;

import com.hrmsystem.employeeprofileservice.dal.dto.education.EducationLevelDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.education.EducationMapper;
import com.hrmsystem.employeeservice.core.dal.model.education.EducationLevel;
import com.hrmsystem.employeeservice.core.dal.repository.education.EducationLevelRepository;
import com.hrmsystem.employeeservice.core.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EducationLevelServiceImpl implements EducationLevelService {

  private static final Logger logger = LoggerFactory.getLogger(EducationLevelServiceImpl.class);

  private final EducationLevelRepository educationLevelRepository;
  private final EducationMapper educationMapper;

  @Override
  @Transactional
  public void createEducationLevel(@Valid EducationLevelDTO educationLevelDTO) {
    logger.info("Creating new education level: {}", educationLevelDTO);
    EducationLevel educationLevel = educationMapper.toEducationLevel(educationLevelDTO);
    educationLevelRepository.save(educationLevel);
    logger.info("Education level created with ID: {}", educationLevel.getId());
  }

  @Override
  @Transactional
  public EducationLevelDTO updateEducationLevel(@Valid EducationLevelDTO educationLevelDTO) {
    logger.info("Updating education level: {}", educationLevelDTO);
    EducationLevel educationLevel = educationLevelRepository.findById(educationLevelDTO.getEducationLevelId())
            .orElseThrow(() -> new EntityNotFoundException("Education level not found for ID: " + educationLevelDTO.getEducationLevelId()));

    educationLevel.setEducationLevelName(educationLevelDTO.getEducationLevelName());
    EducationLevel updatedEducationLevel = educationLevelRepository.save(educationLevel);
    logger.info("Education level updated: {}", updatedEducationLevel);

    return educationMapper.toEducationLevelDTO(updatedEducationLevel);
  }

  @Override
  @Cacheable("educationLevels")
  public EducationLevelDTO findEducationLevelById(Long educationLevelId) {
    logger.info("Finding education level by ID: {}", educationLevelId);
    EducationLevel educationLevel = educationLevelRepository.findById(educationLevelId)
            .orElseThrow(() -> new EntityNotFoundException("Education level not found for ID: " + educationLevelId));

    return educationMapper.toEducationLevelDTO(educationLevel);
  }

  @Override
  @Cacheable("educationLevels")
  public List<EducationLevelDTO> getAllEducationLevels() {
    logger.info("Fetching all education levels");
    List<EducationLevel> educationLevels = educationLevelRepository.findAll();
    return educationLevels.stream()
            .map(educationMapper::toEducationLevelDTO)
            .collect(Collectors.toList());
  }

}

