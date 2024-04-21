package et.hrms.service.impl;

import et.hrms.dal.dto.AdvertisementDTO;
import et.hrms.dal.mapper.AdvertisementMapper;
import et.hrms.dal.model.Advertisement;
import et.hrms.dal.repository.AdvertisementRepository;
import et.hrms.service.AdvertisementService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {

  private final AdvertisementRepository advertisementRepository;
  private final AdvertisementMapper advertisementMapper;

  @Override
  public AdvertisementDTO createAdvertisement(AdvertisementDTO advertisementDTO) {
    Advertisement advertisement = advertisementMapper.toEntity(advertisementDTO);
    advertisement = advertisementRepository.save(advertisement);
    return advertisementMapper.toDto(advertisement);
  }

  @Override
  public AdvertisementDTO findById(Long id) {
    Advertisement advertisement = advertisementRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Advertisement information is not found by this:" + id));
      return advertisementMapper.toDto(advertisement);

  }

  @Override
  public List<AdvertisementDTO> getAll() {
    List<Advertisement> advertisements = advertisementRepository.findAll();
    return advertisements.stream()
            .map(advertisementMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public AdvertisementDTO updateAdvertisement(Long id, AdvertisementDTO advertisementDTO) {
    Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(id);
    if (optionalAdvertisement.isPresent()) {
      Advertisement advertisement = optionalAdvertisement.get();
      advertisementMapper.updateEntity(advertisementDTO, advertisement);
      advertisement = advertisementRepository.save(advertisement);
      return advertisementMapper.toDto(advertisement);
    } else {
      throw new EntityNotFoundException("Advertisement not found with id: " + id);
    }
  }
}
