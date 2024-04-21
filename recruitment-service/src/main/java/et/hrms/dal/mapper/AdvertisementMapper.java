package et.hrms.dal.mapper;

import et.hrms.dal.dto.AdvertisementDTO;
import et.hrms.dal.model.Advertisement;
import et.hrms.dal.model.VacancyNotice;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {VacancyNoticeMapper.class})
public interface AdvertisementMapper {

  AdvertisementDTO toDto(Advertisement advertisement);

  Advertisement toEntity(AdvertisementDTO advertisementDTO);

  default void updateEntity(AdvertisementDTO advertisementDTO, @MappingTarget Advertisement advertisement) {
    if (advertisementDTO == null) {
      return;
    }
    advertisement.setTitle(advertisementDTO.getTitle());
    advertisement.setMedia(advertisementDTO.getMedia());
    advertisement.setPublishDate(advertisementDTO.getPublishDate());
    if (advertisementDTO.getVacancyNoticeDTO() != null) {
      if (advertisement.getVacancyNotice() == null) {
        advertisement.setVacancyNotice(new VacancyNotice());
      }
      getVacancyNoticeMapper().updateEntity(advertisementDTO.getVacancyNoticeDTO(), advertisement.getVacancyNotice());
    } else {
      advertisement.setVacancyNotice(null);
    }
    // Other properties can be updated here
  }
  default VacancyNoticeMapper getVacancyNoticeMapper() {
    return Mappers.getMapper(VacancyNoticeMapper.class);
  }
}
