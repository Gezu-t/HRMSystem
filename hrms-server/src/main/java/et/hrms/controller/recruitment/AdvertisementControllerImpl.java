package et.hrms.controller.recruitment;

import et.hrms.dal.dto.recruitment.AdvertisementDTO;
import et.hrms.service.recruitment.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
@RequiredArgsConstructor
public class AdvertisementControllerImpl implements AdvertisementController {

  private final AdvertisementService advertisementService;

  @Override
  @PostMapping
  public ResponseEntity<AdvertisementDTO> createAdvertisement(@RequestBody AdvertisementDTO advertisementDTO) {
    AdvertisementDTO createdAdvertisement = advertisementService.createAdvertisement(advertisementDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdAdvertisement);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<AdvertisementDTO> getAdvertisement(@PathVariable Long id) {
    AdvertisementDTO advertisementDTO = advertisementService.findById(id);
    return ResponseEntity.ok(advertisementDTO);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<AdvertisementDTO>> getAllAdvertisements() {
    List<AdvertisementDTO> advertisements = advertisementService.getAll();
    return ResponseEntity.ok(advertisements);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<AdvertisementDTO> updateAdvertisement(@PathVariable Long id, @RequestBody AdvertisementDTO advertisementDTO) {
    AdvertisementDTO updatedAdvertisement = advertisementService.updateAdvertisement(id, advertisementDTO);
    return ResponseEntity.ok(updatedAdvertisement);
  }
}