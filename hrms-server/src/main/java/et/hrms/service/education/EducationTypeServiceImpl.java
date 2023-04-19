package et.hrms.service.education;

import et.hrms.dal.dto.education.EducationTypeDTO;
import et.hrms.dal.mapping.EducationDetailMapper;
import et.hrms.dal.model.education.EducationType;
import et.hrms.dal.repository.education.EducationTypeRepository;
import et.hrms.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EducationTypeServiceImpl implements EducationTypeService {

  private final EducationTypeRepository educationTypeRepository;
  private final EducationDetailMapper educationTypeMapper;

  @Override
  public void createEducationType(EducationTypeDTO educationTypeDTO) {
    EducationType educationType = educationTypeRepository.save(educationTypeMapper.toEducationType(educationTypeDTO));
  }

  @Override
  public EducationTypeDTO updateEducationType(EducationTypeDTO educationTypeDTO) {
    EducationType educationType = educationTypeRepository.findById(educationTypeDTO.getEducationTypeId())
            .orElseThrow(() -> new EntityNotFoundException("EducationType not found with ID: " + educationTypeDTO.getEducationTypeId()));

    educationType.setName(educationTypeDTO.getName());

    return educationTypeMapper.toEducationTypeDTO(educationTypeRepository.save(educationType));
  }

  @Override
  public EducationTypeDTO getEducationTypeById(Long educationTypeId) {
    EducationType educationType = educationTypeRepository.findById(educationTypeId)
            .orElseThrow(() -> new EntityNotFoundException("EducationType not found with ID: " + educationTypeId));

    return educationTypeMapper.toEducationTypeDTO(educationType);
  }

  @Override
  public List<EducationTypeDTO> getAllEducationTypes() {
    List<EducationType> educationTypes = educationTypeRepository.findAll();
//    List<EducationTypeDTO> educationTypeDTOS = new ArrayList<>();
//    for (EducationType educationType : educationTypes) {
//      EducationTypeDTO educationTypeDTO = educationTypeMapper.toEducationTypeDTO(educationType);
//      educationTypeDTOS.add(educationTypeDTO);
//    }
    return educationTypes.stream().map(educationTypeMapper::toEducationTypeDTO).toList();
  }
}

