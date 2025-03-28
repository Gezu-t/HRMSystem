package et.hrms.service;

import et.hrms.dal.dto.AdvertisementDTO;

import java.util.List;

public interface AdvertisementService {
  AdvertisementDTO createAdvertisement(AdvertisementDTO advertisementDTO);

  AdvertisementDTO findById(Long id);

  List<AdvertisementDTO> getAll();

  AdvertisementDTO updateAdvertisement(Long id, AdvertisementDTO advertisementDTO);
}
