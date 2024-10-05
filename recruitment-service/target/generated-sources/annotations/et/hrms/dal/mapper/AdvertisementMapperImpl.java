package et.hrms.dal.mapper;

import et.hrms.dal.dto.AdvertisementDTO;
import et.hrms.dal.model.Advertisement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:41+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class AdvertisementMapperImpl implements AdvertisementMapper {

    @Override
    public AdvertisementDTO toDto(Advertisement advertisement) {
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

    @Override
    public Advertisement toEntity(AdvertisementDTO advertisementDTO) {
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
