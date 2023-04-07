package vacancy_notice;

import et.hrms.dal.dto.VacancyNoticeDTO;
import et.hrms.dal.mapping.VacancyNoticeMapper;
import et.hrms.dal.model.VacancyNotice;
import et.hrms.dal.repository.VacancyNoticeRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.impl.VacancyNoticeServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VacancyNoticeServiceTest {

  @Mock
  private VacancyNoticeRepository vacancyNoticeRepository;

  @Mock
  private VacancyNoticeMapper vacancyNoticeMapper;

  @InjectMocks
  private VacancyNoticeServiceImpl vacancyNoticeService;
  private VacancyNotice vacancyNotice1;
  private List<VacancyNotice> vacancyNotices;
  private VacancyNoticeDTO vacancyNoticeDTO;
  private List<VacancyNoticeDTO> vacancyNoticeDTOS;

  @BeforeEach
  void setUp() {
    vacancyNotice1 = new VacancyNotice();
    vacancyNotice1.setId(1L);
    vacancyNotice1.setJobTitle("Job Title 1");

    VacancyNotice vacancyNotice2 = new VacancyNotice();
    vacancyNotice2.setId(2L);
    vacancyNotice2.setJobTitle("Job Title 2");

    vacancyNotices = new ArrayList<>();

    vacancyNotices.add(vacancyNotice1);
    vacancyNotices.add(vacancyNotice2);

    // create some expected vacancy notice DTOs
    vacancyNoticeDTO = new VacancyNoticeDTO();
    vacancyNoticeDTO.setId(1L);
    vacancyNoticeDTO.setJobTitle("Job Title 1");

    VacancyNoticeDTO vacancyNoticeDTO2 = new VacancyNoticeDTO();
    vacancyNoticeDTO2.setId(2L);
    vacancyNoticeDTO2.setJobTitle("Job Title 2");

    vacancyNoticeDTOS = new ArrayList<>();
    vacancyNoticeDTOS.add(vacancyNoticeDTO);
    vacancyNoticeDTOS.add(vacancyNoticeDTO2);


  }

  @Test
  public void testFindAll() {
    // mock the vacancyNoticeRepository to return an empty list
    when(vacancyNoticeRepository.findAll()).thenReturn(Collections.emptyList());

    // assert that the service method throws an EntityNotFoundException
    assertThrows(EntityNotFoundException.class, () -> {
      vacancyNoticeService.findAll();
    });

    // mock the vacancyNoticeRepository to return a list with one item
    when(vacancyNoticeRepository.findAll()).thenReturn(Collections.singletonList(vacancyNotice1));
    when(vacancyNoticeMapper.toDtoList(Collections.singletonList(vacancyNotice1))).thenReturn(Collections.singletonList(vacancyNoticeDTO));

    // assert that the service method returns a list with one item
    List<VacancyNoticeDTO> actualDTOs = vacancyNoticeService.findAll();
    assertEquals(Collections.singletonList(vacancyNoticeDTO), actualDTOs);

    // mock the vacancyNoticeRepository to return a list with two items
    when(vacancyNoticeRepository.findAll()).thenReturn(vacancyNotices);
    when(vacancyNoticeMapper.toDtoList(vacancyNotices)).thenReturn(vacancyNoticeDTOS);

    // assert that the service method returns a list with two items
    actualDTOs = vacancyNoticeService.findAll();
    assertEquals(vacancyNoticeDTOS, actualDTOs);
  }
  @Test
  public void testFindAll1() {
    // mock the vacancyNoticeRepository to return an empty list of vacancy notices when its findAll() method is called
    when(vacancyNoticeRepository.findAll()).thenReturn(Collections.emptyList());

    // assert that the findAll() method throws an EntityNotFoundException when called with an empty list of vacancy notices
    assertThrows(EntityNotFoundException.class, () -> vacancyNoticeService.findAll());

    // mock the vacancyNoticeRepository to return a non-empty list of vacancy notices when its findAll() method is called
    when(vacancyNoticeRepository.findAll()).thenReturn(vacancyNotices);

    // mock the vacancyNoticeMapper to return the expected DTOs when its toDtoList() method is called
    when(vacancyNoticeMapper.toDtoList(vacancyNotices)).thenReturn(vacancyNoticeDTOS);

    // call the findAll() method and verify that the expected DTOs are returned
    List<VacancyNoticeDTO> actualDTOs = vacancyNoticeService.findAll();
    assertEquals(vacancyNoticeDTOS, actualDTOs, "DTO lists are not equal.");
  }



  @Test
  public void testSave() {
    when(vacancyNoticeMapper.toEntity(vacancyNoticeDTO)).thenReturn(vacancyNotice1);
    when(vacancyNoticeRepository.save(vacancyNotice1)).thenReturn(vacancyNotice1);
    when(vacancyNoticeMapper.toDto(vacancyNotice1)).thenReturn(vacancyNoticeDTO);

    VacancyNoticeDTO savedVacancyNotice = vacancyNoticeService.save(vacancyNoticeDTO);
    assertEquals(vacancyNoticeDTO.getJobTitle(), savedVacancyNotice.getJobTitle());
    verify(vacancyNoticeRepository, times(1)).save(any(VacancyNotice.class));
  }
}