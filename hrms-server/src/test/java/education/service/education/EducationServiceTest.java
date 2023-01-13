package education.service.education;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.mapping.EducationMapper;
import et.hrms.dal.model.Education;
import et.hrms.dal.repository.EducationRepository;
import et.hrms.exceptions.RecordNotFoundException;
import et.hrms.service.EducationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class EducationServiceTest {


    @Mock
    private EducationRepository educationRepository;
    // Create an instance of the EducationService implementation class

    @InjectMocks
    private final EducationMapper educationMapper = mock(EducationMapper.class);

    @Mock
    private EducationService educationService;


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

        Education education = new Education();
        education = educationMapper.toEducation(educationDTO);

        assertEquals(education.getDegree(),(educationDTO.getDegree()));
        when(educationRepository.save(education)).thenThrow(RecordNotFoundException.class);
        try{
            educationService.createEducation(educationDTO);
        }catch(RecordNotFoundException e){
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
        //update the mock EducationInfo object


    }


}
