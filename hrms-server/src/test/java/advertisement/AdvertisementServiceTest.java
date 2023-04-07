package advertisement;

import et.hrms.dal.dto.AdvertisementDTO;
import et.hrms.dal.dto.VacancyNoticeDTO;
import et.hrms.dal.mapping.AdvertisementMapper;
import et.hrms.dal.model.Advertisement;
import et.hrms.dal.repository.AdvertisementRepository;
import et.hrms.service.impl.AdvertisementServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdvertisementServiceTest {

  @Mock
  private AdvertisementRepository advertisementRepository;

  @Mock
  private AdvertisementMapper advertisementMapper;

  @InjectMocks
  private AdvertisementServiceImpl advertisementService;

  private AdvertisementDTO advertisementDTO;
  private VacancyNoticeDTO vacancyNoticeDTO;
  private Advertisement advertisement;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    advertisementDTO = new AdvertisementDTO();

    advertisementDTO.setId(1L);
    advertisementDTO.setTitle("Test Title");
    advertisementDTO.setMedia("Test Media");
    advertisementDTO.setPublishDate(LocalDate.now());

    vacancyNoticeDTO = new VacancyNoticeDTO();
    vacancyNoticeDTO.setId(1L);
    vacancyNoticeDTO.setJobTitle("Test Vacancy Notice Title");
    vacancyNoticeDTO.setJobDescription("Test Vacancy Notice Title");
    advertisementDTO.setVacancyNoticeDTO(vacancyNoticeDTO);

    advertisement = new Advertisement();
    advertisement.setId(1L);
    advertisement.setTitle(advertisementDTO.getTitle());
    advertisement.setMedia(advertisementDTO.getMedia());
    advertisement.setPublishDate(advertisementDTO.getPublishDate());
  }

  @Test
  public void testCreateAdvertisement() {
    // given
    when(advertisementRepository.save(advertisement)).thenReturn(advertisement);
    when(advertisementMapper.toEntity(advertisementDTO)).thenReturn(advertisement);
    when(advertisementMapper.toDto(advertisement)).thenReturn(advertisementDTO);
    // when
    AdvertisementDTO createdAdvertisement = advertisementService.createAdvertisement(advertisementDTO);
    // then
    verify(advertisementRepository).save(advertisement);
    verify(advertisementMapper).toEntity(advertisementDTO);
    verify(advertisementMapper).toDto(advertisement);

//    assertEquals(createdAdvertisement.getTitle(), advertisementDTO.getTitle());
//    assertEquals(createdAdvertisement.getMedia(), advertisementDTO.getMedia());
  }

  @Test
  public void testFindById() {
    // given
    Advertisement ad = new Advertisement();
    ad.setId(1L);
    ad.setTitle("Java Developer");
    ad.setMedia("Linkedin");
    ad.setPublishDate(LocalDate.now());
    AdvertisementDTO dto = new AdvertisementDTO();
    dto.setId(1L);
    dto.setTitle("Java Developer");
    dto.setMedia("Linkedin");
    dto.setPublishDate(LocalDate.now());
    when(advertisementRepository.findById(ad.getId())).thenReturn(Optional.ofNullable(advertisement));
    when(advertisementMapper.toDto(advertisement)).thenReturn(advertisementDTO);
    // when
    AdvertisementDTO foundAdvertisement = advertisementService.findById(ad.getId());
    // then
    verify(advertisementRepository).findById(dto.getId());
    verify(advertisementMapper).toDto(ad);
    assertNotNull(foundAdvertisement);
  }

  @Test
  public void testUpdateAdvertisement() {
    // given
    Long id = 1L;
    AdvertisementDTO advertisementDTO = new AdvertisementDTO();
    advertisementDTO.setTitle("Updated Title");
    advertisementDTO.setMedia("Updated Media");
    advertisementDTO.setPublishDate(LocalDate.now().plusDays(1));

    VacancyNoticeDTO vacancyNoticeDTO = new VacancyNoticeDTO();
    vacancyNoticeDTO.setId(2L);
    vacancyNoticeDTO.setJobTitle("Updated Vacancy Notice Title");
    advertisementDTO.setVacancyNoticeDTO(vacancyNoticeDTO);

    Advertisement advertisement = new Advertisement();
    advertisement.setId(id);
    advertisement.setTitle("Original Title");
    advertisement.setMedia("Original Media");
    advertisement.setPublishDate(LocalDate.now());

    when(advertisementRepository.findById(id)).thenReturn(Optional.of(advertisement));
    when(advertisementRepository.save(any(Advertisement.class))).thenReturn(advertisement);
    when(advertisementMapper.toDto(any(Advertisement.class))).thenReturn(advertisementDTO);

    // when
    AdvertisementDTO updatedAdvertisement = advertisementService.updateAdvertisement(id, advertisementDTO);

    // then
    verify(advertisementRepository).findById(id);
    verify(advertisementRepository).save(any(Advertisement.class));
    verify(advertisementMapper).toDto(any(Advertisement.class));

    assertEquals(updatedAdvertisement.getTitle(), advertisementDTO.getTitle());
    assertEquals(updatedAdvertisement.getMedia(), advertisementDTO.getMedia());
    assertEquals(updatedAdvertisement.getPublishDate(), advertisementDTO.getPublishDate());
  }
}
