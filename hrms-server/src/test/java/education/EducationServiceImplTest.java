package education;
import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.mapping.EducationMapper;
import et.hrms.dal.model.Education;
import et.hrms.dal.repository.EducationRepository;
import et.hrms.service.impl.EducationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EducationServiceImplTest {


    @Mock
    private EducationRepository educationRepository;

    @Mock
    private EducationMapper educationMapper;

    @InjectMocks
    private EducationServiceImpl educationService;

    private EducationDTO educationDTO;
    private Education education;

    @BeforeEach
    public void setUp() {
        String startDate = "01-09-2021";
        String endDate = "12-05-2023";
        String completionDate = "01-01-2022";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate educationStartDate = LocalDate.parse(startDate, formatter);
        LocalDate educationEndDate = LocalDate.parse(endDate, formatter);
        LocalDate completionDate1 = LocalDate.parse(completionDate, formatter);

        educationDTO = new EducationDTO();
        educationDTO.setEducationId(1L);
        educationDTO.setInstitution("Institution");
        educationDTO.setDegree("Degree");
        educationDTO.setEducationCompletionDate(completionDate1);
        educationDTO.setEducationStartDate(educationStartDate);

        education = new Education();
        education.setId(1L);
        education.setEducationCompletionDate(completionDate1);
        education.setEducationStartDate(educationStartDate);
        education.setDegree("Bachelor's Degree");
        education.setInstitution("Test Institution 1");
        education.setDegree("Test Degree 1");
        education.setEducationMinor("Computer Science");
        education.setEducationMinor("Mathematics");
        education.setEducationGrade("A");
        education.setEducationGradePointAverage("4.0");
        education.setAwardDate(educationEndDate);
        education.setAwardDescription("Award Description 1");

        education.setCreatedAt(LocalDateTime.of(2022, 3, 20, 12, 0, 0));
        education.setUpdatedAt(LocalDateTime.of(2022, 3, 21, 12, 0, 0));

    }

    @Test
    public void testCreateEducation() {
        when(educationMapper.toEducation(educationDTO)).thenReturn(education);
        when(educationRepository.save(education)).thenReturn(education);

        educationService.createEducation(educationDTO);

        verify(educationRepository, times(1)).save(education);
    }

    @Test
    public void testUpdateEducationInfo() {
        when(educationRepository.findById(1L)).thenReturn(Optional.of(education));
        when(educationRepository.save(education)).thenReturn(education);
        when(educationMapper.toEducationDTO(education)).thenReturn(educationDTO);

        EducationDTO updatedEducationDTO = educationService.updateEducationInfo(educationDTO);

        assertEquals(educationDTO, updatedEducationDTO);
//        verify(educationRepository, times(1)).findById(1L);
        verify(educationRepository, times(1)).save(education);
        verify(educationMapper, times(1)).toEducationDTO(education);
    }

    @Test
    public void testGetEducationByInstitution() {
        when(educationRepository.findByInstitution("Institution")).thenReturn(education);
        when(educationMapper.toEducationDTO(education)).thenReturn(educationDTO);

        EducationDTO resultDTO = educationService.getEducationByInstitution("Institution");

        assertEquals(educationDTO, resultDTO);
        verify(educationRepository, times(1)).findByInstitution("Institution");
        verify(educationMapper, times(1)).toEducationDTO(education);
    }

    @Test
    public void testGetAllEducationList() {
        List<Education> educations = Arrays.asList(education);
        Page<Education> educationPage = new PageImpl<>(educations);
        Pageable pageable = PageRequest.of(0, 5, Sort.by("institution").ascending());

        when(educationRepository.findAll(pageable)).thenReturn(educationPage);
        when(educationMapper.toEducationDTO(education)).thenReturn(educationDTO);

        List<EducationDTO> educationDTOs = educationService.getAllEducationList(0, 5, Sort.by("institution").ascending());

        assertEquals(1, educationDTOs.size());
        assertEquals(educationDTO, educationDTOs.get(0));
        verify(educationRepository, times(1)).findAll(pageable);
        verify(educationMapper, times(1)).toEducationDTO(education);
    }

//    @Test
//    public void testValidateEducationDTOInvalidStartDate() {
//        String startDate = "01-09-2021";
//        String endDate = "12-05-2023";
//        String completionDate = "01-01-2022";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//        LocalDate educationStartDate = LocalDate.parse(startDate, formatter);
//        LocalDate educationEndDate = LocalDate.parse(endDate, formatter);
//        LocalDate completionDate1 = LocalDate.parse(completionDate, formatter);
//
//        educationDTO.setEducationStartDate(educationStartDate);
//        educationDTO.setEducationEndDate(educationEndDate);
//
//        assertThrows(IllegalArgumentException.class, () -> educationService.createEducation(educationDTO));
//        assertThrows(IllegalArgumentException.class, () -> educationService.updateEducationInfo(educationDTO));
//    }

    @Test
    public void testValidateEducationDTOInvalidInstitution() {
        educationDTO.setInstitution(null);

        assertThrows(NullPointerException.class, () -> educationService.createEducation(educationDTO));
        assertThrows(NullPointerException.class, () -> educationService.updateEducationInfo(educationDTO));
    }

    @Test
    public void testValidateEducationDTOInvalidDegree() {
        educationDTO.setDegree(null);

        assertThrows(NullPointerException.class, () -> educationService.createEducation(educationDTO));
        assertThrows(NullPointerException.class, () -> educationService.updateEducationInfo(educationDTO));
    }



}