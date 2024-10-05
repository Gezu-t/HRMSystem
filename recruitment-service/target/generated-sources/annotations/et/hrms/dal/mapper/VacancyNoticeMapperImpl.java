package et.hrms.dal.mapper;

import et.hrms.dal.dto.AdvertisementDTO;
import et.hrms.dal.dto.VacancyNoticeDTO;
import et.hrms.dal.model.Advertisement;
import et.hrms.dal.model.VacancyNotice;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:41+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class VacancyNoticeMapperImpl implements VacancyNoticeMapper {

    @Override
    public VacancyNoticeDTO toDto(VacancyNotice vacancyNotice) {
        if ( vacancyNotice == null ) {
            return null;
        }

        VacancyNoticeDTO vacancyNoticeDTO = new VacancyNoticeDTO();

        vacancyNoticeDTO.setId( vacancyNotice.getId() );
        vacancyNoticeDTO.setJobTitle( vacancyNotice.getJobTitle() );
        vacancyNoticeDTO.setJobDescription( vacancyNotice.getJobDescription() );
        vacancyNoticeDTO.setNumberOfPositions( vacancyNotice.getNumberOfPositions() );
        vacancyNoticeDTO.setPublishDate( vacancyNotice.getPublishDate() );
        vacancyNoticeDTO.setCloseDate( vacancyNotice.getCloseDate() );
        vacancyNoticeDTO.setAdvertisement( advertisementToAdvertisementDTO( vacancyNotice.getAdvertisement() ) );

        return vacancyNoticeDTO;
    }

    @Override
    public VacancyNotice toEntity(VacancyNoticeDTO vacancyNoticeDTO) {
        if ( vacancyNoticeDTO == null ) {
            return null;
        }

        VacancyNotice vacancyNotice = new VacancyNotice();

        vacancyNotice.setId( vacancyNoticeDTO.getId() );
        vacancyNotice.setJobTitle( vacancyNoticeDTO.getJobTitle() );
        vacancyNotice.setJobDescription( vacancyNoticeDTO.getJobDescription() );
        vacancyNotice.setNumberOfPositions( vacancyNoticeDTO.getNumberOfPositions() );
        vacancyNotice.setPublishDate( vacancyNoticeDTO.getPublishDate() );
        vacancyNotice.setCloseDate( vacancyNoticeDTO.getCloseDate() );
        vacancyNotice.setAdvertisement( advertisementDTOToAdvertisement( vacancyNoticeDTO.getAdvertisement() ) );

        return vacancyNotice;
    }

    @Override
    public List<VacancyNoticeDTO> toDtoList(List<VacancyNotice> vacancyNotices) {
        if ( vacancyNotices == null ) {
            return null;
        }

        List<VacancyNoticeDTO> list = new ArrayList<VacancyNoticeDTO>( vacancyNotices.size() );
        for ( VacancyNotice vacancyNotice : vacancyNotices ) {
            list.add( toDto( vacancyNotice ) );
        }

        return list;
    }

    @Override
    public List<VacancyNotice> toEntityList(List<VacancyNoticeDTO> vacancyNoticeDTOs) {
        if ( vacancyNoticeDTOs == null ) {
            return null;
        }

        List<VacancyNotice> list = new ArrayList<VacancyNotice>( vacancyNoticeDTOs.size() );
        for ( VacancyNoticeDTO vacancyNoticeDTO : vacancyNoticeDTOs ) {
            list.add( toEntity( vacancyNoticeDTO ) );
        }

        return list;
    }

    protected AdvertisementDTO advertisementToAdvertisementDTO(Advertisement advertisement) {
        if ( advertisement == null ) {
            return null;
        }

        AdvertisementDTO advertisementDTO = new AdvertisementDTO();

        advertisementDTO.setId( advertisement.getId() );
        advertisementDTO.setTitle( advertisement.getTitle() );
        advertisementDTO.setMedia( advertisement.getMedia() );
        advertisementDTO.setPublishDate( advertisement.getPublishDate() );

        return advertisementDTO;
    }

    protected Advertisement advertisementDTOToAdvertisement(AdvertisementDTO advertisementDTO) {
        if ( advertisementDTO == null ) {
            return null;
        }

        Advertisement advertisement = new Advertisement();

        advertisement.setId( advertisementDTO.getId() );
        advertisement.setTitle( advertisementDTO.getTitle() );
        advertisement.setMedia( advertisementDTO.getMedia() );
        advertisement.setPublishDate( advertisementDTO.getPublishDate() );

        return advertisement;
    }
}
