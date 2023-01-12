package et.hrms.service;

import et.hrms.dal.dto.JobDTO;

import java.util.List;

public interface JobService {
    void saveJob(JobDTO jobDTO);

    JobDTO updateJob(JobDTO jobDTO);

    JobDTO getJobById(Long id) throws Exception;

    List<JobDTO> getAllJobs(int page, int size);
}
