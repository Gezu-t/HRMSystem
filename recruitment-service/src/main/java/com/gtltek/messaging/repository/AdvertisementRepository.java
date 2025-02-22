package com.gtltek.messaging.repository;

import com.gtltek.messaging.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
