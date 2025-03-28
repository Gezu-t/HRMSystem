package com.hrmsystem.employeeprofileservice.controller.job;

import com.hrmsystem.employeeprofileservice.dal.dto.job.JobDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface JobController {
  ResponseEntity<Void> createJob(@Valid @RequestBody JobDTO jobDTO);

  ResponseEntity<JobDTO> getJobById(@PathVariable("jobId") Long jobId);
  ResponseEntity<JobDTO> updateJob(@PathVariable("jobId") Long jobId, @Valid @RequestBody JobDTO jobDTO);

  ResponseEntity<List<JobDTO>> getAllJobs(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id, Asc") String[] sort);
}
