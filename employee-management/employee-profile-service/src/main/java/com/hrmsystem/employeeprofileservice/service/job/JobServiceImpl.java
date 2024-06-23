package com.hrmsystem.employeeprofileservice.service.job;


import com.hrmsystem.employeeprofileservice.dal.dto.job.JobDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.JobMapper;
import com.hrmsystem.employeeservice.core.dal.model.job.Job;
import com.hrmsystem.employeeservice.core.dal.repository.job.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final JobMapper jobMapper;


    @Override
    public void saveJob(JobDTO jobDTO) {
        Job job = jobMapper.toJob(jobDTO);

        jobRepository.save(job);
    }

    @Override
    public JobDTO updateJob(Long jobId, JobDTO jobDTO) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Job not found by this ID: " + jobId ));

        // Update the properties of the Job entity with the values from the DTO
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setMinSalary(jobDTO.getMinSalary());
        job.setMaxSalary(jobDTO.getMaxSalary());

        // Save the updated Job entity to the database
        job = jobRepository.save(job);

        // Convert the updated Job entity back to a DTO and return it
        return jobMapper.toJobDTO(job);
    }


    @Override
    public JobDTO getJobById(Long id)  {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job detail is not found by this id: " + id));

        return jobMapper.toJobDTO(job);
    }


    @Override
    public List<JobDTO> getAllJobs(int page, int size, Sort sort) {

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Job> jobs = jobRepository.findAll(pageable);
        return jobs.stream().map(jobMapper::toJobDTO)
                .toList();

    }


}
