package com.gtltek.employee.core.dal.repository.job;

import com.gtltek.employee.core.dal.model.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
