package et.hrms.service.education;

import et.hrms.dal.dto.education.EducationDTO;
import et.hrms.dal.dto.education.EducationDetailDTO;
import et.hrms.dal.mapping.EducationDetailMapper;
import et.hrms.dal.mapping.EducationMapper;
import et.hrms.dal.model.education.*;
import et.hrms.dal.model.employee.Employee;
import et.hrms.dal.repository.education.EducationAwardRepository;
import et.hrms.dal.repository.education.EducationLevelRepository;
import et.hrms.dal.repository.education.EducationRepository;
import et.hrms.dal.repository.education.EducationTypeRepository;
import et.hrms.dal.repository.employee.EmployeeRepository;
import et.hrms.exceptions.EntityNotFoundException;
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
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EducationServiceImpl implements EducationService {

  private final EducationRepository educationRepository;
  private final EducationLevelRepository educationLevelRepository;
  private final EducationAwardRepository educationAwardRepository;
  private final EducationTypeRepository educationTypeRepository;
  private final EmployeeRepository employeeRepository;
  private final EducationMapper educationMapper;
  private final EducationDetailMapper educationDetailMapper;

  /**
   * Create the employee education information based the employee id specified
   *
   * @param employeeId   it uses to create specific education system
   * @param educationDTO It uses the object of EducationDTO
   */
  @Override
  public void createEducation(Long employeeId, EducationDTO educationDTO) {
    validateEducationDTO(educationDTO);
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));
    Education education = educationMapper.toEducation(educationDTO);
    education.setCreatedAt(LocalDateTime.now());
    EducationLevel educationLevel = educationLevelRepository.findById(educationDTO.getEducationLevelId())
            .orElseThrow(() -> new EntityNotFoundException("EducationLevel not found with ID: " + educationDTO.getEducationLevelId()));
    education.setEducationLevel(educationLevel);
    education.setEmployee(employee);
    if (educationDTO.getEducationAwardId() != null) {
      EducationAward educationAward = educationAwardRepository.findById(educationDTO.getEducationAwardId())
              .orElseThrow(() -> new EntityNotFoundException("EducationAward not found with ID: " + educationDTO.getEducationAwardId()));
      education.setEducationAward(educationAward);
    }
    education.setCreatedAt(LocalDateTime.now());
    List<EducationDetail> educationDetails = new ArrayList<>();
    for (EducationDetailDTO educationDetailDTO : educationDTO.getEducationDetailDTOS()) {
      EducationDetail educationDetail = educationDetailMapper.toEducationDetail(educationDetailDTO);
      EducationType educationType = educationTypeRepository.findById(educationDetailDTO.getEducationTypeId())
              .orElseThrow(() -> new EntityNotFoundException("EducationType not found with ID: " + educationDetailDTO.getEducationTypeId()));
      educationDetail.setEducationType(educationType);
      educationDetail.setEducation(education);
      educationDetails.add(educationDetail);
    }
    education.setEducationDetails(educationDetails);
    educationRepository.save(education);
  }

  @Override
  public EducationDTO updateEducationInfo(Long employeeId, EducationDTO educationDTO) {
    validateEducationDTO(educationDTO);
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));
    Education education = educationRepository.findById(educationDTO.getEducationId())
            .orElseThrow(() -> new EntityNotFoundException("Education information is not found by this id: " + educationDTO.getEducationId()));
    if (educationDTO.getEducationLevelId() != null) {
      EducationLevel educationLevel = educationLevelRepository.findById(educationDTO.getEducationLevelId())
              .orElseThrow(() -> new EntityNotFoundException("Education level is not found by this ID: " + educationDTO.getEducationLevelId()));
      education.setEducationLevel(educationLevel);
    } else {
      education.setEducationLevel(null);
    }
    education.setEmployee(employee);
    if (educationDTO.getEducationAwardId() != null) {

      EducationAward educationAward = educationAwardRepository.findById(educationDTO.getEducationAwardId())
              .orElseThrow(() -> new EntityNotFoundException("EducationAward not found with ID: " + educationDTO.getEducationAwardId()));
      education.setEducationAward(educationAward);
    } else {
      education.setEducationAward(null);
    }
    List<EducationDetail> educationDetails = education.getEducationDetails();
    for (EducationDetailDTO educationDetailDTO : educationDTO.getEducationDetailDTOS()) {
//      EducationDetail educationDetail = educationDetails.stream()
//              .filter(ed -> ed.getId().equals(educationDetailDTO.getEducationDetailId()))
//              .findFirst()
//              .orElseThrow(() -> new EntityNotFoundException("EducationDetail not found with ID: " + educationDetailDTO.getEducationDetailId()));
     EducationDetail educationDetail = educationDetailMapper.toEducationDetail(educationDetailDTO);

      EducationType educationType = educationTypeRepository.findById(educationDetailDTO.getEducationTypeId())
              .orElseThrow(() -> new EntityNotFoundException("EducationType not found with ID: " + educationDetailDTO.getEducationTypeId()));
      educationDetail.setEducationType(educationType);
    }
    updateEducationFields(education, educationDTO);
    education.setUpdatedAt(LocalDateTime.now());
    Education updatedEducation = educationRepository.save(education);
    return educationMapper.toEducationDTO(updatedEducation);
  }

  private void validateEducationDTO(EducationDTO educationDTO) {
    Objects.requireNonNull(educationDTO.getInstitution(), "Institution name must not be null");
    if (!(educationDTO.getStartDate() == null || educationDTO.getEndDate() == null)) {
      throw new IllegalArgumentException("Education start date must be before education end date");
    }
  }

  private void updateEducationFields(Education education, EducationDTO educationDTO) {
    BeanUtils.copyProperties(educationDTO, education, "id", "createdAt", "updatedAt");
  }

  @Override
  public EducationDTO getEducationByInstitution(String name) {
    return educationMapper.toEducationDTO(educationRepository.findByInstitution(name));
  }

  @Override
  public List<EducationDTO> getAllEducationList(int page, int size, Sort sort) {
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Education> educations = educationRepository.findAll(pageable);

    return  educations.stream()
            .map(education -> {
              EducationDTO educationDTO = educationMapper.toEducationDTO(education);
              List<EducationDetailDTO> educationDetailDTOS = education.getEducationDetails().stream()
                      .map(educationDetailMapper::toEducationDetailDTO)
                      .collect(Collectors.toList());
              educationDTO.setEducationDetailDTOS(educationDetailDTOS);
              return educationDTO;
            })
            .collect(Collectors.toList());

  }


}
