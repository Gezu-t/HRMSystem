package com.gtltek.messaging.repository;

import com.gtltek.messaging.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
