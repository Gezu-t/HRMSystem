package vacancy_notice;

import et.hrms.dal.dto.VacancyNoticeDTO;
import et.hrms.dal.mapper.VacancyNoticeMapper;
import et.hrms.dal.model.VacancyNotice;
import et.hrms.dal.repository.VacancyNoticeRepository;
import et.hrms.service.impl.VacancyNoticeServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VacancyNoticeServiceTest {

  @InjectMocks
  private VacancyNoticeServiceImpl vacancyNoticeService;
  @Mock
  private VacancyNoticeRepository vacancyNoticeRepository;
  @Mock
  private VacancyNoticeMapper vacancyNoticeMapper;
  private VacancyNotice vacancyNotice1;

  @BeforeEach
  void setUp() {
    vacancyNotice1 = new VacancyNotice();
    vacancyNotice1.setId(1L);
    vacancyNotice1.setJobTitle("Job Title 1");

    VacancyNotice vacancyNotice2 = new VacancyNotice();
    vacancyNotice2.setId(2L);
    vacancyNotice2.setJobTitle("Job Title 2");



    // create some expected vacancy notice DTOs
    VacancyNoticeDTO vacancyNoticeDTO = new VacancyNoticeDTO();
    vacancyNoticeDTO.setId(1L);
    vacancyNoticeDTO.setJobTitle("Job Title 1");

    VacancyNoticeDTO vacancyNoticeDTO2 = new VacancyNoticeDTO();
    vacancyNoticeDTO2.setId(2L);
    vacancyNoticeDTO2.setJobTitle("Job Title 2");

  }

  @Test
  public void testFindAll() {
    VacancyNoticeDTO noticeDTO = new VacancyNoticeDTO();
    // mock the vacancyNoticeRepository to return an empty list
    when(vacancyNoticeRepository.findAll()).thenReturn(Collections.emptyList());
    // assert that the service method throws an EntityNotFoundException
    assertThrows(EntityNotFoundException.class, () -> vacancyNoticeService.findAll());
    // mock the vacancyNoticeRepository to return a list with one item
    when(vacancyNoticeRepository.findAll()).thenReturn(Collections.singletonList(vacancyNotice1));
    when(vacancyNoticeMapper.toDtoList(Collections.singletonList(vacancyNotice1)))
            .thenReturn(Collections.singletonList(noticeDTO));
    // assert that the service method returns a list with one item
    List<VacancyNoticeDTO> actualDTOs = vacancyNoticeService.findAll();
    assertEquals(Collections.singletonList(noticeDTO), actualDTOs);

  }



  @Test
  public void testSave() {
    VacancyNoticeDTO noticeDTO = new VacancyNoticeDTO();
    when(vacancyNoticeMapper.toEntity(noticeDTO)).thenReturn(vacancyNotice1);
    when(vacancyNoticeRepository.save(vacancyNotice1)).thenReturn(vacancyNotice1);
    when(vacancyNoticeMapper.toDto(vacancyNotice1)).thenReturn(noticeDTO);

    VacancyNoticeDTO savedVacancyNotice = vacancyNoticeService.save(noticeDTO);
    assertEquals(noticeDTO.getJobTitle(), savedVacancyNotice.getJobTitle());
    verify(vacancyNoticeRepository, times(1)).save(vacancyNotice1);
  }
}