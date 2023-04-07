package vacancy_notice;

import et.hrms.dal.dto.VacancyNoticeDTO;
import et.hrms.dal.mapping.VacancyNoticeMapper;
import et.hrms.dal.model.VacancyNotice;
import et.hrms.dal.repository.VacancyNoticeRepository;
import et.hrms.service.impl.VacancyNoticeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

  @Test
  public void testFindAll() {
    VacancyNotice vacancyNotice1 = new VacancyNotice();
    vacancyNotice1.setId(1L);
    vacancyNotice1.setJobTitle("Job Title 1");

    VacancyNotice vacancyNotice2 = new VacancyNotice();
    vacancyNotice2.setId(2L);
    vacancyNotice2.setJobTitle("Job Title 2");

    List<VacancyNotice> vacancyNotices = Arrays.asList(vacancyNotice1, vacancyNotice2);

//    when(vacancyNoticeRepository.findAll()).thenReturn(vacancyNotices);

    VacancyNoticeDTO vacancyNoticeDTO1 = new VacancyNoticeDTO();
    vacancyNoticeDTO1.setId(1L);
    vacancyNoticeDTO1.setJobTitle("Job Title 1");

    VacancyNoticeDTO vacancyNoticeDTO2 = new VacancyNoticeDTO();
    vacancyNoticeDTO2.setId(2L);
  }

  @Test
  public void testSave() {
    VacancyNoticeDTO vacancyNoticeDTO = new VacancyNoticeDTO();
    vacancyNoticeDTO.setJobTitle("Job Title");

    VacancyNotice vacancyNotice = new VacancyNotice();
    vacancyNotice.setJobTitle("Job Title");

    when(vacancyNoticeMapper.toEntity(vacancyNoticeDTO)).thenReturn(vacancyNotice);
    when(vacancyNoticeRepository.save(vacancyNotice)).thenReturn(vacancyNotice);
    when(vacancyNoticeMapper.toDto(vacancyNotice)).thenReturn(vacancyNoticeDTO);

    VacancyNoticeDTO savedVacancyNotice = vacancyNoticeService.save(vacancyNoticeDTO);
    assertEquals(vacancyNoticeDTO.getJobTitle(), savedVacancyNotice.getJobTitle());
    verify(vacancyNoticeRepository, times(1)).save(any(VacancyNotice.class));
  }
}