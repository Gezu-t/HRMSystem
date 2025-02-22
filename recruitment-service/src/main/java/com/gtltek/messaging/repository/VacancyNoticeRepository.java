package com.gtltek.messaging.repository;

import com.gtltek.messaging.model.VacancyNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyNoticeRepository extends JpaRepository<VacancyNotice, Long> {
}
