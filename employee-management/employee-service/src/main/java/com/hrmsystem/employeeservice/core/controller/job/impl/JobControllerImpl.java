package com.hrmsystem.employeeservice.core.controller.job.impl;

import com.hrmsystem.employeeservice.core.controller.job.JobController;
import com.hrmsystem.employeeservice.core.exceptions.EntityNotFoundException;
import dal.dto.job.JobDTO;
import com.hrmsystem.employeeservice.core.service.job.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jobs")
public class JobControllerImpl implements JobController {
  private final JobService jobService;

  @Override
  @PostMapping
  public ResponseEntity<Void> createJob(@Valid @RequestBody JobDTO jobDTO) {
    jobService.saveJob(jobDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @GetMapping("/{jobId}")
  public ResponseEntity<JobDTO> getJobById(@PathVariable("jobId") Long jobId) {
    JobDTO jobDTO = jobService.getJobById(jobId);
    return new ResponseEntity<>(jobDTO, HttpStatus.OK);
  }

  @Override
  @PutMapping("/{jobId}")
  public ResponseEntity<JobDTO> updateJob(@PathVariable("jobId") Long jobId, @Valid @RequestBody JobDTO jobDTO) {
    JobDTO updateJob = jobService.updateJob(jobId, jobDTO);
    return new ResponseEntity<>(updateJob, HttpStatus.OK);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<JobDTO>> getAllJobs(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(defaultValue = "id, Asc") String[] sort) {
    Sort sortOrder = Sort.by(sort[0]);
    if (sort.length > 1) {
      sortOrder = sort[1].equalsIgnoreCase("desc") ? sortOrder.descending() : sortOrder.ascending();
    }
    try {
      List<JobDTO> jobDTOS = jobService.getAllJobs(page, size, sortOrder);
      return new ResponseEntity<>(jobDTOS, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
