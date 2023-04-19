package et.hrms.service.recruitment;

import et.hrms.dal.dto.recruitment.AdvertisementDTO;

import java.util.List;

public interface AdvertisementService {
  AdvertisementDTO createAdvertisement(AdvertisementDTO advertisementDTO);

  AdvertisementDTO findById(Long id);

  List<AdvertisementDTO> getAll();

  AdvertisementDTO updateAdvertisement(Long id, AdvertisementDTO advertisementDTO);
}
