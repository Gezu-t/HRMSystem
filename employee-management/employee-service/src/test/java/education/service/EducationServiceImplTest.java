package education.service;


import com.hrms.employee.core.dal.dto.education.*;
import com.hrms.employee.core.dal.mapping.education.EducationDetailMapper;
import com.hrms.employee.core.dal.mapping.education.EducationMapper;
import com.hrms.employee.core.service.education.impl.EducationServiceImpl;
import com.hrms.employee.core.dal.model.education.Education;
import com.hrms.employee.core.dal.model.education.EducationDetail;
import com.hrms.employee.core.dal.model.education.EducationLevel;
import com.hrms.employee.core.dal.model.education.EducationType;
import com.hrms.employee.core.dal.model.employee.Employee;
import com.hrms.employee.core.dal.repository.education.EducationLevelRepository;
import com.hrms.employee.core.dal.repository.education.EducationRepository;
import com.hrms.employee.core.dal.repository.education.EducationTypeRepository;
import com.hrms.employee.core.dal.repository.employee.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EducationServiceImplTest {

  @Mock
  private EducationRepository educationRepository;
  @Mock
  private EmployeeRepository employeeRepository;
  @Mock
  private EducationLevelRepository educationLevelRepository;
  @Mock
  private EducationTypeRepository educationTypeRepository;
  @Mock
  private EducationMapper educationMapper;
  @Mock
  private EducationDetailMapper educationDetailMapper;
  @InjectMocks
  private EducationServiceImpl educationService;

  private EducationDTO educationDTO;
  private Education education;
  private Employee employee;

  @BeforeEach
  void setUp() {
    String completionDate = "01-01-2022";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate completionDate1 = LocalDate.parse(completionDate, formatter);
    EducationLevelDTO educationLevelDTO = new EducationLevelDTO();
    educationLevelDTO.setEducationLevelId(1L);
    educationLevelDTO.setEducationLevelName("Bachelor");

    educationDTO = new EducationDTO();
    educationDTO.setCompletionDate(completionDate1);
    educationDTO.setEducationLevelId(1L);
    educationDTO.setInstitution("Test University");
    educationDTO.setStartDate(LocalDate.now());
    educationDTO.setEndDate(LocalDate.now().plusYears(4));

    EducationDetailDTO educationDetailDTO = new EducationDetailDTO();
    educationDetailDTO.setDegree("Bachelor of Science");
    educationDetailDTO.setMajor("Computer Science");
    educationDetailDTO.setMinor("Mathematics");
    educationDetailDTO.setGrade("A");

    EducationTypeDTO educationTypeDTO = new EducationTypeDTO();
    educationTypeDTO.setEducationTypeId(1L);
    educationTypeDTO.setName("Engineering");

    EducationAwardDTO educationAwardDTO = new EducationAwardDTO();
    educationAwardDTO.setName("Dean's List");
    educationAwardDTO.setDate(LocalDate.now().plusYears(2));
    educationAwardDTO.setDescription("Top 10% of students in the faculty");
    education = new Education();
    education.setId(1L);
    education.setInstitution("Unical");
    education.setCreatedAt(LocalDateTime.of(2022, 3, 20, 12, 0, 0));
    education.setUpdatedAt(LocalDateTime.of(2022, 3, 21, 12, 0, 0));

    employee = new Employee();
    employee.setId(1L);
    education.setEmployee(employee);

  }

  @Test
  void testCreateEducation() {
    EducationDTO educationDTO = new EducationDTO();
    educationDTO.setCompletionDate(LocalDate.of(2022, 4, 20));
    educationDTO.setInstitution("University of Test");
    educationDTO.setEducationLevelId(1L);
    EducationType educationType = new EducationType();
    educationType.setId(1L);
    EducationDetailDTO educationDetailDTO = new EducationDetailDTO();
    educationDetailDTO.setDegree("Bachelor");
    educationDetailDTO.setMajor("Computer Science");
    educationDetailDTO.setGpa("3.5");
    educationDetailDTO.setEducationTypeId(1L);
    education.setCreatedAt(LocalDateTime.now());
    EducationDetail educationDetail = new EducationDetail();
    educationDetail.setId(1L);
    educationDetail.setEducationType(educationType);

    List<EducationDetailDTO> educationDetailDTOList = new ArrayList<>();
    educationDetailDTOList.add(educationDetailDTO);
    educationDTO.setEducationDetailDTOS(educationDetailDTOList);

    when(educationLevelRepository.findById(1L)).thenReturn(Optional.of(new EducationLevel()));
    when(educationTypeRepository.findById(1L)).thenReturn(Optional.of(new EducationType()));
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee()));
    when(educationMapper.toEducation(educationDTO)).thenReturn(education);
    when(educationDetailMapper.toEducationDetail(educationDetailDTO)).thenReturn(educationDetail);

    educationService.createEducation(1L, educationDTO);

    verify(educationRepository, times(1)).save(any(Education.class));
  }


  @Test
  void testUpdateEducationInfo() {
    EducationDTO educationDTO = new EducationDTO();
    educationDTO.setEducationId(1L);
    educationDTO.setCompletionDate(LocalDate.of(2022, 4, 20));
    educationDTO.setInstitution("University of Test");
    educationDTO.setEducationLevelId(1L);
    educationDTO.setEmployeeId(1L);

    EducationDetailDTO educationDetailDTO = new EducationDetailDTO();
    educationDetailDTO.setEducationDetailId(1L);
    educationDetailDTO.setDegree("Bachelor");
    educationDetailDTO.setMajor("Computer Science");
    educationDetailDTO.setGpa("3.5");
    educationDetailDTO.setEducationTypeId(1L);

    List<EducationDetailDTO> educationDetailDTOList = new ArrayList<>();
    educationDetailDTOList.add(educationDetailDTO);
    educationDTO.setEducationDetailDTOS(educationDetailDTOList);

    EducationDetail educationDetail = new EducationDetail();
    educationDetail.setEducation(education);

//    when(educationDetailRepository.findById(1L)).thenReturn(Optional.of(new EducationDetail()));
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee()));
    when(educationRepository.findById(1L)).thenReturn(Optional.of(new Education()));
    when(educationLevelRepository.findById(1L)).thenReturn(Optional.of(new EducationLevel()));
    when(educationTypeRepository.findById(1L)).thenReturn(Optional.of(new EducationType()));
    when(educationDetailMapper.toEducationDetail(educationDetailDTO)).thenReturn(new EducationDetail());

    educationService.updateEducationInfo(employee.getId(), educationDTO);

    verify(educationRepository, times(1)).save(any(Education.class));
  }


  @Test
  void testGetEducationByInstitution() {
    when(educationRepository.findByInstitution("Institution")).thenReturn(education);
    when(educationMapper.toEducationDTO(education)).thenReturn(educationDTO);

    EducationDTO resultDTO = educationService.getEducationByInstitution("Institution");

    assertEquals(educationDTO, resultDTO);
    verify(educationRepository, times(1)).findByInstitution("Institution");
    verify(educationMapper, times(1)).toEducationDTO(education);
  }

  @Test
  void testGetAllEducation() {
    int page = 0;
    int size = 10;
    Sort sort = Sort.by("completionDate").descending();
    PageRequest pageable = PageRequest.of(page, size, sort);
    Education education1 = new Education();
    education1.setId(1L);
    education1.setCompletionDate(LocalDate.of(2021, 1, 1));
    education1.setInstitution("University A");

    Education education2 = new Education();
    education2.setId(2L);
    education2.setCompletionDate(LocalDate.of(2022, 1, 1));
    education2.setInstitution("University B");

    List<Education> educationList = new ArrayList<>();
    educationList.add(education1);
    educationList.add(education2);

    Page<Education> educationPage = new PageImpl<>(educationList, pageable, educationList.size());

    when(educationRepository.findAll(pageable)).thenReturn(educationPage);


    EducationDetail educationDetail1 = new EducationDetail();
    educationDetail1.setId(1L);
    educationDetail1.setDegree("Bachelor");
    educationDetail1.setMajor("Computer Science");
    educationDetail1.setGpa("3.5");
    educationDetail1.setEducation(education1);

    EducationDetail educationDetail2 = new EducationDetail();
    educationDetail2.setId(2L);
    educationDetail2.setDegree("Master");
    educationDetail2.setMajor("Computer Engineering");
    educationDetail2.setGpa("3.9");
    educationDetail2.setEducation(education2);

    List<EducationDetail> educationDetailList = new ArrayList<>();
    educationDetailList.add(educationDetail1);
    educationDetailList.add(educationDetail2);
    education1.setEducationDetails(educationDetailList);
    education2.setEducationDetails(educationDetailList);

    when(educationMapper.toEducationDTO(education1)).thenReturn(educationDTO);
    when(educationMapper.toEducationDTO(education2)).thenReturn(educationDTO);
    when(educationDetailMapper.toEducationDetailDTO(educationDetail1)).thenReturn(any());

    List<EducationDTO> educationDTOPage = educationService.getAllEducationList(page, size, sort);

  }


  @Test
  void testValidateEducationDTOInvalidInstitution() {
    educationDTO.setInstitution(null);
    educationDTO.setStartDate(null);
    educationDTO.setEndDate(null);
    employee.setId(null);

    assertThrows(NullPointerException.class, () -> educationService.createEducation(employee.getId(), educationDTO));
    assertThrows(NullPointerException.class, () -> educationService.updateEducationInfo(employee.getId(), educationDTO));
  }

  @Test
  void testValidateEducationDTOInvalidDegree() {
    educationDTO.setStartDate(null);
    educationDTO.setEndDate(null);
    educationDTO.setInstitution(null);
    employee.setId(null);

    assertThrows(NullPointerException.class, () -> educationService.createEducation(employee.getId(), educationDTO));
    assertThrows(NullPointerException.class, () -> educationService.updateEducationInfo(employee.getId(), educationDTO));
  }


}