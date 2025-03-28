package com.hrmsystem.employeeprofileservice.service.job;

import com.hrmsystem.employeeprofileservice.dal.dto.job.JobDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface JobService {
    void saveJob(JobDTO jobDTO);

    JobDTO updateJob(Long jobId, JobDTO jobDTO);

    JobDTO getJobById(Long id);

    List<JobDTO> getAllJobs(int page, int size, Sort sort);
}
