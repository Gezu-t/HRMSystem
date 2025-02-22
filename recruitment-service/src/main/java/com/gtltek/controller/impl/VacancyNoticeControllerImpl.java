package com.gtltek.controller.impl;

import com.gtltek.controller.VacancyNoticeController;
import com.gtltek.messaging.dto.VacancyNoticeDTO;
import com.gtltek.service.VacancyNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vacancy-notices")
public class VacancyNoticeControllerImpl implements VacancyNoticeController {
  private final VacancyNoticeService vacancyNoticeService;

  @Override
  @GetMapping
  public ResponseEntity<List<VacancyNoticeDTO>> getAllVacancyNotices() {
    List<VacancyNoticeDTO> vacancyNotices = vacancyNoticeService.findAll();
    return ResponseEntity.ok(vacancyNotices);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<VacancyNoticeDTO> getVacancyNoticeById(@PathVariable Long id) {
    VacancyNoticeDTO vacancyNotice = vacancyNoticeService.findById(id);
    return ResponseEntity.ok(vacancyNotice);
  }

  @Override
  @PostMapping
  public ResponseEntity<VacancyNoticeDTO> createVacancyNotice(@RequestBody VacancyNoticeDTO vacancyNoticeDTO) {
    VacancyNoticeDTO savedVacancyNotice = vacancyNoticeService.save(vacancyNoticeDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedVacancyNotice);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<VacancyNoticeDTO> updateVacancyNotice(@PathVariable Long id, @RequestBody VacancyNoticeDTO vacancyNoticeDTO) {
    vacancyNoticeDTO.setId(id);
    VacancyNoticeDTO updatedVacancyNotice = vacancyNoticeService.save(vacancyNoticeDTO);
    return ResponseEntity.ok(updatedVacancyNotice);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVacancyNotice(@PathVariable Long id) {
    vacancyNoticeService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}