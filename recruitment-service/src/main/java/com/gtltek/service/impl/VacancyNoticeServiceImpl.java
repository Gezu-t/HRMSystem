package com.gtltek.service.impl;

import com.gtltek.messaging.dto.VacancyNoticeDTO;
import com.gtltek.messaging.mapper.VacancyNoticeMapper;
import com.gtltek.messaging.model.VacancyNotice;
import com.gtltek.messaging.repository.VacancyNoticeRepository;
import com.gtltek.service.VacancyNoticeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VacancyNoticeServiceImpl implements VacancyNoticeService {

  private final VacancyNoticeRepository vacancyNoticeRepository;
  private final VacancyNoticeMapper vacancyNoticeMapper;


  @Override
  public List<VacancyNoticeDTO> findAll() {
    List<VacancyNotice> vacancyNotices = vacancyNoticeRepository.findAll();
    if (vacancyNotices == null || vacancyNotices.isEmpty()) {
      throw new EntityNotFoundException("VacancyNotices is not found");
    }
    List<VacancyNoticeDTO> vacancyNoticeDTOs = vacancyNoticeMapper.toDtoList(vacancyNotices);
    if (vacancyNoticeDTOs == null || vacancyNoticeDTOs.isEmpty()) {
      throw new EntityNotFoundException("VacancyNoticeDTOs is not found");
    }
    return vacancyNoticeDTOs;
  }


  @Override
  public VacancyNoticeDTO findById(Long id) {
    VacancyNotice vacancyNotice = vacancyNoticeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Vacancy Notice not found with id: " + id));
    return vacancyNoticeMapper.toDto(vacancyNotice);
  }

  @Override
  public VacancyNoticeDTO save(VacancyNoticeDTO vacancyNoticeDTO) {
    VacancyNotice vacancyNotice = vacancyNoticeMapper.toEntity(vacancyNoticeDTO);
    VacancyNotice savedVacancyNotice = vacancyNoticeRepository.save(vacancyNotice);
    return vacancyNoticeMapper.toDto(savedVacancyNotice);
  }

  @Override
  public void deleteById(Long id) {
    if (!vacancyNoticeRepository.existsById(id)) {
      throw new EntityNotFoundException("Vacancy Notice not found with id: " + id);
    }
    vacancyNoticeRepository.deleteById(id);
  }
}
