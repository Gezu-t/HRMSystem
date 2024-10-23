package education.service;


import com.hrmsystem.employeeservice.core.exceptions.EntityNotFoundException;
import dal.dto.education.EducationLevelDTO;
import com.hrmsystem.employeeservice.core.dal.mapping.education.EducationMapper;
import com.hrmsystem.employeeservice.core.service.education.impl.EducationLevelServiceImpl;
import dal.model.education.EducationLevel;
import dal.repository.education.EducationLevelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EducationLevelServiceTest {

  @Mock
  private EducationLevelRepository educationLevelRepository;

  @Mock
  private EducationMapper educationMapper;

  @InjectMocks
  private EducationLevelServiceImpl educationLevelService;

  @Test
  public void testCreateEducationLevel() {
    // create input data
    EducationLevelDTO educationLevelDTO = new EducationLevelDTO();
    educationLevelDTO.setEducationLevelName("Bachelor's degree");

    EducationLevel educationLevel = new EducationLevel();
    educationLevel.setEducationLevelName("Bachelor's degree");

    // configure mock objects
    when(educationMapper.toEducationLevel(educationLevelDTO)).thenReturn(educationLevel);
    when(educationLevelRepository.save(educationLevel)).thenReturn(educationLevel);

    // call method being tested
    educationLevelService.createEducationLevel(educationLevelDTO);

    // verify mock interactions
    verify(educationMapper).toEducationLevel(educationLevelDTO);
    verify(educationLevelRepository).save(educationLevel);
  }

  @Test
  public void testUpdateEducationLevel() {
    // create input data
    EducationLevelDTO educationLevelDTO = new EducationLevelDTO();
    educationLevelDTO.setEducationLevelId(1L);
    educationLevelDTO.setEducationLevelName("Master's degree");

    EducationLevel existingEducationLevel = new EducationLevel();
    existingEducationLevel.setId(1L);
    existingEducationLevel.setEducationLevelName("Bachelor's degree");

    // configure mock objects
    when(educationLevelRepository.findById(1L)).thenReturn(Optional.of(existingEducationLevel));
    when(educationMapper.toEducationLevelDTO(existingEducationLevel)).thenReturn(educationLevelDTO);
    when(educationLevelRepository.save(existingEducationLevel)).thenReturn(existingEducationLevel);

    // call method being tested
    EducationLevelDTO updatedEducationLevelDTO = educationLevelService.updateEducationLevel(educationLevelDTO);

    // verify mock interactions
    verify(educationLevelRepository).findById(1L);
    verify(educationMapper).toEducationLevelDTO(existingEducationLevel);
    verify(educationLevelRepository).save(existingEducationLevel);

    // verify results
    assertEquals(educationLevelDTO, updatedEducationLevelDTO);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testUpdateEducationLevelNotFound() {
    // create input data
    EducationLevelDTO educationLevelDTO = new EducationLevelDTO();
    educationLevelDTO.setEducationLevelId(1L);
    educationLevelDTO.setEducationLevelName("Master's degree");

    // configure mock objects
    when(educationLevelRepository.findById(1L)).thenReturn(Optional.empty());

    // call method being tested
    educationLevelService.updateEducationLevel(educationLevelDTO);
  }

  @Test
  public void testGetAllEducationLevelList() {
    // create input data
    EducationLevel educationLevel1 = new EducationLevel();
    educationLevel1.setId(1L);
    educationLevel1.setEducationLevelName("Bachelor's degree");

    EducationLevel educationLevel2 = new EducationLevel();
    educationLevel2.setId(2L);
    educationLevel2.setEducationLevelName("Master's degree");

    List<EducationLevel> educationLevelList = new ArrayList<>();
    educationLevelList.add(educationLevel1);
    educationLevelList.add(educationLevel2);

    EducationLevelDTO educationLevelDTO1 = new EducationLevelDTO();
    educationLevelDTO1.setEducationLevelId(1L);
    educationLevelDTO1.setEducationLevelName("Bachelor's degree");

    EducationLevelDTO educationLevelDTO2 = new EducationLevelDTO();
    educationLevelDTO2.setEducationLevelId(2L);
    educationLevelDTO2.setEducationLevelName("Master's degree");

    List<EducationLevelDTO> educationLevelDTOList = new ArrayList<>();

  }
}

