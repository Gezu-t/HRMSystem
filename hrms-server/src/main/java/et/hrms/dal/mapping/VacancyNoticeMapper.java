package et.hrms.dal.mapping;

import et.hrms.dal.dto.recruitment.VacancyNoticeDTO;
import et.hrms.dal.model.recruitment.Advertisement;
import et.hrms.dal.model.recruitment.VacancyNotice;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VacancyNoticeMapper {

    VacancyNoticeMapper INSTANCE = Mappers.getMapper(VacancyNoticeMapper.class);

    VacancyNoticeDTO toDto(VacancyNotice vacancyNotice);
    VacancyNotice toEntity(VacancyNoticeDTO vacancyNoticeDTO);
    List<VacancyNoticeDTO> toDtoList(List<VacancyNotice> vacancyNotices);
    List<VacancyNotice> toEntityList(List<VacancyNoticeDTO> vacancyNoticeDTOs);

    default void updateEntity(VacancyNoticeDTO vacancyNoticeDTO, @MappingTarget VacancyNotice vacancyNotice) {
        if (vacancyNoticeDTO == null) {
            return;
        }
        vacancyNotice.setJobTitle(vacancyNoticeDTO.getJobTitle());
        vacancyNotice.setJobDescription(vacancyNoticeDTO.getJobDescription());
        if (vacancyNoticeDTO.getAdvertisement() != null) {
            if (vacancyNotice.getAdvertisement() == null) {
                vacancyNotice.setAdvertisement(new Advertisement());
            }
            getAdvertisementMapper().updateEntity(vacancyNoticeDTO.getAdvertisement(), vacancyNotice.getAdvertisement());
        } else {
            vacancyNotice.setAdvertisement(null);
        }
        // Other properties can be updated here
    }

    default AdvertisementMapper getAdvertisementMapper() {
        return Mappers.getMapper(AdvertisementMapper.class);
    }
}
