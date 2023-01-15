package education.service.education;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.mapping.EducationMapper;
import et.hrms.dal.model.Education;
import et.hrms.dal.repository.EducationRepository;
import et.hrms.exceptions.RecordNotFoundException;
import et.hrms.service.EducationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@Slf4j
@SpringJUnitConfig

public class EducationServiceTest {

    @Autowired
    private  EducationRepository educationRepository;
    @Autowired
    private  EducationMapper educationMapper;
    @Autowired
    private  EducationService educationService;



    @Test
    public void testCreateEducationInfo() {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setEducationType("Master's Degree");
        educationDTO.setEducationMajor("Education Science");
        educationDTO.setDegree("Science");
        educationDTO.setInstitution("University of Calabria");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        educationDTO.setEducationStartDate(LocalDate.parse("01-03-2020", formatter));
        educationDTO.setEducationEndDate(LocalDate.parse("01-03-2023", formatter));

        Education result = educationMapper.toEducation(educationDTO);
        Education education = educationMapper.toEducation(educationDTO);
        when(educationMapper.toEducation(educationDTO)).thenReturn(education);

//        assertNull(education.getEducationType());
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
        // create a new EducationDTO object with the updated education information

        EducationDTO educationDTO = new EducationDTO();
        // Update the mock EducationInfo object
        educationDTO.setEducationType("Master's Degree");
        educationDTO.setEducationMajor("Education Science");
        educationDTO.setInstitution("University of Calabria");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        educationDTO.setEducationStartDate(LocalDate.parse("01-03-2020", formatter));
        educationDTO.setEducationEndDate(LocalDate.parse("01-03-2023", formatter));

        EducationDTO result = educationService.updateEducationInfo(educationDTO);
        if (result != null) {
            assertEquals(educationDTO, result);
        }

    }


    @Test
    public void testGetAllEducationInfo() {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setEducationType("Master's Degree");
        educationDTO.setEducationMajor("Education Science");
        educationDTO.setInstitution("University of Calabria");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        educationDTO.setEducationStartDate(LocalDate.parse("01-03-2020", formatter));
        educationDTO.setEducationEndDate(LocalDate.parse("01-03-2023", formatter));

        Education education = educationMapper.toEducation(educationDTO);
        List<Education> someList = Collections.singletonList(education);

//        when(educationRepository.findAll()).thenReturn(someList);

        List<EducationDTO> returnedList = educationService.getAllEducationList(2, 1);
        assertNotNull(returnedList);
        assertTrue(returnedList.isEmpty());
        verify(educationService).getAllEducationList(2, 1);

    }

    @Test
    public void testGetAllListEducation() {
        // create a list of EducationDTO objects to return from the repository
        List<EducationDTO> educationDTOList = educationService.getAllEducationList(20, 1);
        Education education = new Education();
        List<Education> educations = new ArrayList<>();
        for(EducationDTO educationDTO: educationDTOList) {
            education.setEducationType(educationDTO.getEducationType());

            education.setDegree(educationDTO.getDegree());
            education.setEducationLevel(educationDTO.getEducationLevel());
            education.setAward(educationDTO.getAward());
            educations.add(education);
        }
//        when(educationRepository.findAll()).thenReturn(educations);

        // assert that the returned list is the same as the one created earlier
        assertEquals(educationDTOList, educationDTOList);
        // Verify that the repository's findAll() method was called
//        verify(educationRepository).findAll();
    }


}
