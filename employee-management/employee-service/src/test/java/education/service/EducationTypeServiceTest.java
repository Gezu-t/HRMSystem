package education.service;


import com.hrms.employee.core.dal.dto.education.EducationTypeDTO;
import com.hrms.employee.core.dal.mapping.education.EducationDetailMapper;
import com.hrms.employee.core.service.education.impl.EducationTypeServiceImpl;
import com.hrms.employee.core.dal.model.education.EducationType;
import com.hrms.employee.core.dal.repository.education.EducationTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EducationTypeServiceTest {

  @Mock
  private EducationTypeRepository educationTypeRepository;
  @Mock
  private EducationDetailMapper educationTypeMapper;
  @InjectMocks
  private EducationTypeServiceImpl educationTypeService;

  @Test
  public void testCreateEducationType() {
    EducationTypeDTO educationTypeDTO = new EducationTypeDTO();
    educationTypeDTO.setName("Bachelor");
    when(educationTypeMapper.toEducationType(educationTypeDTO)).thenReturn(new EducationType());

    educationTypeService.createEducationType(educationTypeDTO);

    verify(educationTypeRepository, times(1)).save(any(EducationType.class));
  }


  @Test
  public void testUpdateEducationType() {
    // Create a mock EducationTypeDTO object
    EducationTypeDTO educationTypeDTO = new EducationTypeDTO();
    educationTypeDTO.setEducationTypeId(1L);
    educationTypeDTO.setName("Bachelor's Degree");

    // Create a mock EducationType object
    EducationType educationType = new EducationType();
    educationType.setId(1L);
    educationType.setName("Bachelor's Degree");

    // Configure the mock repository to return the mock EducationType object
    when(educationTypeRepository.findById(1L)).thenReturn(Optional.of(educationType));
    // Call the service method
    educationTypeService.updateEducationType(educationTypeDTO);

    // Verify that the repository save method was called
    verify(educationTypeRepository).save(any(EducationType.class));
  }

  @Test
  public void testGetAllEducationTypeList() {
    // Create a mock EducationType object
    EducationType educationType = new EducationType();
    educationType.setId(1L);
    educationType.setName("Bachelor's Degree");

    // Create a mock EducationTypeDTO object
    EducationTypeDTO educationTypeDTO = new EducationTypeDTO();
    educationTypeDTO.setEducationTypeId(1L);
    educationTypeDTO.setName("Bachelor's Degree");

    List<EducationType> educationTypeList = new ArrayList<>();
    educationTypeList.add(educationType);

    // Configure the mock repository to return a list of EducationType objects
    when(educationTypeRepository.findAll()).thenReturn(Collections.singletonList(educationType));

    // Configure the mock mapper to return a list of EducationTypeDTO objects
    when(educationTypeMapper.toEducationTypeDTO(educationType)).thenReturn(educationTypeDTO);

    // Call the service method
    List<EducationTypeDTO> educationTypeDTOList = educationTypeService.getAllEducationTypes();

    // Verify that the repository findAll method was called
    verify(educationTypeRepository).findAll();

    assertEquals(educationTypeList.size(), educationTypeDTOList.size());

    for (int i = 0; i < educationTypeDTOList.size(); i++) {
      educationTypeDTO = educationTypeDTOList.get(i);
      educationType = educationTypeList.get(i);

      assertEquals(educationType.getId(), educationTypeDTO.getEducationTypeId());
      assertEquals(educationType.getName(), educationTypeDTO.getName());
    }
  }

}
