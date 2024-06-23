package com.hrmsystem.employeeservice.core.dal.repository.job;

import com.hrmsystem.employeeservice.core.dal.model.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
