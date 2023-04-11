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
  private Advertisement advertisement;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    advertisementDTO = new AdvertisementDTO();

    advertisementDTO.setId(1L);
    advertisementDTO.setTitle("Test Title");
    advertisementDTO.setMedia("Test Media");
    advertisementDTO.setPublishDate(LocalDate.now());

    VacancyNoticeDTO vacancyNoticeDTO = new VacancyNoticeDTO();
    vacancyNoticeDTO.setId(1L);
    vacancyNoticeDTO.setJobTitle("Test Vacancy Notice Title");
    vacancyNoticeDTO.setJobDescription("Test Vacancy Notice Title");
    advertisementDTO.setVacancyNoticeDTO(vacancyNoticeDTO);

    advertisement = new Advertisement();

    advertisement.setId(1L);
    advertisement.setTitle(advertisementDTO.getTitle());
    advertisement.setMedia(advertisementDTO.getMedia());
    advertisement.setPublishDate(advertisementDTO.getPublishDate());

    advertisementMapper.toDto(advertisement);
    advertisementMapper.toEntity(advertisementDTO);
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
    Advertisement advert = new Advertisement();
    advert.setId(1L);
    AdvertisementDTO advertDTO = new AdvertisementDTO();
    advertDTO.setId(1L);
    when(advertisementRepository.findById(advert.getId())).thenReturn(Optional.ofNullable(advert));
    when(advertisementMapper.toDto(advert)).thenReturn(advertDTO);
    // when
    AdvertisementDTO foundAdvertisement = advertisementService.findById(advert.getId());
    // then
    verify(advertisementRepository).findById(advertDTO.getId());
    verify(advertisementMapper).toDto(advert);
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
