package com.hrms.employee.core.dal.repository.job;

import com.hrms.employee.core.dal.model.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
