package com.gtltek.controller;

import com.gtltek.messaging.dto.AdvertisementDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AdvertisementController {
  @PostMapping
  ResponseEntity<AdvertisementDTO> createAdvertisement(@RequestBody AdvertisementDTO advertisementDTO);

  @GetMapping("/{id}")
  ResponseEntity<AdvertisementDTO> getAdvertisement(@PathVariable Long id);

  @GetMapping
  ResponseEntity<List<AdvertisementDTO>> getAllAdvertisements();

  @PutMapping("/{id}")
  ResponseEntity<AdvertisementDTO> updateAdvertisement(@PathVariable Long id, @RequestBody AdvertisementDTO advertisementDTO);
}
