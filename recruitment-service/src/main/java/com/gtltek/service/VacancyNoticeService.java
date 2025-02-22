package com.gtltek.service;

import com.gtltek.messaging.dto.VacancyNoticeDTO;

import java.util.List;

public interface VacancyNoticeService {
    List<VacancyNoticeDTO> findAll();
    VacancyNoticeDTO findById(Long id);
    VacancyNoticeDTO save(VacancyNoticeDTO vacancyNotice);
    void deleteById(Long id);


}
