package et.hrms.service.education;

import et.hrms.dal.dto.education.EducationLevelDTO;
import et.hrms.dal.mapping.education.EducationMapper;
import et.hrms.dal.model.education.EducationLevel;
import et.hrms.dal.repository.education.EducationLevelRepository;
import et.hrms.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class EducationLevelServiceImpl implements EducationLevelService {

  private final EducationLevelRepository educationLevelRepository;
  private final EducationMapper educationMapper;


  @Override
  public void createEducationLevel(EducationLevelDTO educationLevelDTO) {
    EducationLevel educationLevel = educationLevelRepository.save(educationMapper.toEducationLevel(educationLevelDTO));
  }

  @Override
  public EducationLevelDTO updateEducationLevel(EducationLevelDTO educationLevelDTO) {
    EducationLevel educationLevel = educationLevelRepository.findById(educationLevelDTO.getEducationLevelId())
            .orElseThrow(() -> new EntityNotFoundException("Education level is not found by this ID: " + educationLevelDTO.getEducationLevelId()));
    educationLevel.setEducationLevelName(educationLevelDTO.getEducationLevelName());
    EducationLevel updatedEducationLevel = educationLevelRepository.save(educationLevel);
    return educationMapper.toEducationLevelDTO(updatedEducationLevel);
  }

  @Override
  public EducationLevelDTO findEducationLevelById(Long educationLevelId) {
    EducationLevel educationLevel = educationLevelRepository.findById(educationLevelId)
            .orElseThrow(() -> new EntityNotFoundException("Education level is not found by this ID: " + educationLevelId));

    return educationMapper.toEducationLevelDTO(educationLevel);
  }

  @Override
  public List<EducationLevelDTO> getAllEducationLevel() {
    List<EducationLevel> educationLevels = educationLevelRepository.findAll();
    return educationLevels.stream()
            .map(educationMapper::toEducationLevelDTO)
            .collect(Collectors.toList());
  }



}
