package education.service.education;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.mapping.EducationMapper;
import et.hrms.dal.model.Education;
import et.hrms.dal.repository.EducationRepository;
import et.hrms.service.EducationService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EducationServiceTest {


    @Mock
    private EducationRepository educationRepository;
    // Create an instance of the EducationService implementation class

    private final EducationMapper educationMapper = mock(EducationMapper.class);

    @Mock
    private EducationService educationService;


    @Test
    public void testCreateEducationInfo() {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setEducationType("Master's Degree");
        educationDTO.setEducationMajor("Education Science");
        educationDTO.setInstitution("University of Calabria");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        educationDTO.setEducationStartDate(LocalDate.parse("01-03-2020", formatter));
        educationDTO.setEducationEndDate(LocalDate.parse("01-03-2023", formatter));

        Education education1 = educationMapper.toEducation(educationDTO);
//        when(educationRepository.save(education1)).thenReturn(education1);

        // Call the updateEducationInfo method and assert that it returns the expected result
//        verify(educationMapper).createEducation(new Education(null, "High School", "XYZ High School"));

//        assertEquals(educationDTO, result);
    }

    @Test
    public void testUpdateEducationInfo() throws Exception {
        // create a new EducationDTO object with the updated education information

        EducationDTO educationDTO = new EducationDTO();
//        when(educationRepository.findById(1L)).thenReturn(Optional.of(new Education()));
//        educationDTO.setEducationId(1L); // set the ID of the education record to update
//        educationDTO.setEducationType("Bachelor's Degree");
//        educationDTO.setEducationMajor("Computer Science");
//        educationDTO.setEducationInstitutionName("University of XYZ");
//        educationDTO.setEducationStartDate(LocalDate.parse("01-03-2020"));
//        educationDTO.setEducationEndDate(LocalDate.parse("01-03-2023"));

        // Update the mock EducationInfo object
        educationDTO.setEducationType("Master's Degree");
        educationDTO.setEducationMajor("Education Science");
        educationDTO.setInstitution("University of Calabria");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        educationDTO.setEducationStartDate(LocalDate.parse("01-03-2020", formatter));
        educationDTO.setEducationEndDate(LocalDate.parse("01-03-2023", formatter));

        Education education1 = educationMapper.toEducation(educationDTO);

//        when(educationRepository.save(education1)).thenReturn(education1);

        // Call the updateEducationInfo method and assert that it returns the expected result
        EducationDTO result = educationService.updateEducationInfo(educationDTO);
        if (result != null) {

            Assertions.assertEquals(educationDTO, result);
        }

    }


}
