package education;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.mapping.EducationMapper;
import et.hrms.dal.model.Education;
import et.hrms.dal.repository.EducationRepository;
import et.hrms.exceptions.RecordNotFoundException;
import et.hrms.service.EducationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@Slf4j
@SpringJUnitConfig
public class EducationServiceTest {

    @Mock
    private EducationRepository educationRepository = mock(EducationRepository.class);
    @Mock
    private EducationMapper educationMapper = mock(EducationMapper.class);

    @InjectMocks
    private EducationService educationService = mock(EducationService.class);

private Education education;
private EducationDTO educationDTO;


    @BeforeEach
    public void init() {

        education = new Education();
        education.setDegree("Science");

        educationDTO = new EducationDTO();
        educationDTO.setEducationType("Master's Degree");
        educationDTO.setEducationMajor("Education Science");
        educationDTO.setDegree("Science");
        educationDTO.setInstitution("University of Calabria");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        educationDTO.setEducationStartDate(LocalDate.parse("01-03-2020", formatter));
        educationDTO.setEducationEndDate(LocalDate.parse("01-03-2023", formatter));


        educationService = mock(EducationService.class);
        educationMapper = mock(EducationMapper.class);


    }

    @Test
    public void testCreateEducationInfo() {
        education = educationMapper.toEducation(educationDTO);
        when(educationMapper.toEducation(educationDTO)).thenReturn(education);
        assertEquals("Science", educationDTO.getDegree());
        assertNotNull(educationDTO.getDegree());
        when(educationRepository.save(education)).thenThrow(RecordNotFoundException.class);
        try {
            educationService.createEducation(educationDTO);
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testUpdateEducationInfo() throws Exception {
        education = educationMapper.toEducation(educationDTO);
        when(educationService.updateEducationInfo(educationDTO)).thenReturn(null);
        assertEquals(educationService.updateEducationInfo(any()), educationService.getEducationByInstitution(any()));
        EducationDTO result = educationService.updateEducationInfo(educationDTO);
        if (result != null) {
            assertEquals(educationDTO, result);
        }

    }


    @Test
    public void testGetAllEducationInfo() {
        List<Education> someList = Collections.singletonList(education);
        List<EducationDTO> returnedList = educationService.getAllEducationList(2, 1);
        assertNotNull(returnedList);
        assertTrue(returnedList.isEmpty());
        verify(educationService).getAllEducationList(2, 1);

    }

    @Test
    public void testGetAllListEducation() {
        when(educationService.getAllEducationList(20, 1)).thenReturn(null);
        List<EducationDTO> educations = educationService.getAllEducationList(20, 0);
        for(EducationDTO edu : educations){
            Education education = educationMapper.toEducation(educationDTO);
            assertNull(education.getEducationGrade());
        }

    }


}
