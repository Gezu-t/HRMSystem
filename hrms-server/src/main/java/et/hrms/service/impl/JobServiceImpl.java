package et.hrms.service.impl;


import et.hrms.dal.dto.JobDTO;
import et.hrms.dal.mapping.JobMapper;
import et.hrms.dal.model.Job;
import et.hrms.dal.repository.JobRepository;
import et.hrms.service.JobService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public JobDTO updateJob(JobDTO jobDTO) {
        Job job = jobRepository.findById(jobDTO.getJobId())
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

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
    public List<JobDTO> getAllJobs(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobs = jobRepository.findAll(pageable);
        return jobs.stream().map(jobMapper::toJobDTO)
                .toList();

    }


}
