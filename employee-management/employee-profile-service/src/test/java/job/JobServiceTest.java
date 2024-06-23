package job;

import com.hrmsystem.employeeprofileservice.dal.dto.job.JobDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.JobMapper;
import com.hrmsystem.employeeprofileservice.service.job.JobServiceImpl;
import com.hrmsystem.employeeservice.core.dal.model.job.Job;
import com.hrmsystem.employeeservice.core.dal.repository.job.JobRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceTest {

  @Mock
  private JobRepository jobRepository;
  @Mock
  private JobMapper jobMapper;
  @InjectMocks
  private JobServiceImpl jobService;

  private Job job;
  private JobDTO jobDTO;
  private List<JobDTO> jobDTOS;
  private List<Job> jobList;
  @Before
  public void setUp(){
    job = new Job();
    job.setId(1L);

    jobDTO = new JobDTO();
    jobDTO.setJobId(1L);

    jobDTOS = new ArrayList<>();
    jobDTOS.add(jobDTO);

    jobList = new ArrayList<>();
    jobList.add(job);

  }

  @Test
  public void testCreateJob(){
    when(jobMapper.toJob(jobDTO)).thenReturn(job);
    jobService.saveJob(jobDTO);
    verify(jobRepository, times(1)).save(any(Job.class));
  }

  @Test
  public void testGetJobById(){
    Long jobId = 1L;
    // Configure the mock repository to return the mock Job object
    when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
    when(jobMapper.toJobDTO(job)).thenReturn(jobDTO);

    JobDTO resultDTO = jobService.getJobById(jobId);

    assertEquals(jobDTO, resultDTO);
    verify(jobRepository, times(1)).findById(jobId);
    verify(jobMapper, times(1)).toJobDTO(job);


  }

  @Test
  public void testUpdateJob(){
    Long jobId = 1L;
    // Configure the mock repository to return the mock Job object
    when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
    // Call the service method
    jobService.updateJob(jobId, jobDTO);

    // Verify that the repository save method was called
    verify(jobRepository).save(any(Job.class));
  }

  @Test
  public void testGetAllJobs(){
    int page = 0;
    int size = 10;
    Sort sort = Sort.by("title").descending();
    PageRequest pageable = PageRequest.of(page, size, sort);

    Page<Job> jobPage = new PageImpl<>(jobList, pageable, jobList.size());

    when(jobRepository.findAll(pageable)).thenReturn(jobPage);

    // Call the method under test
    List<JobDTO> result = jobService.getAllJobs(0, 10,  sort);
    // Verify the interactions
    verify(jobRepository).findAll(pageable);
    verify(jobMapper).toJobDTO(job);

    // Assert the result
    assertEquals(1, jobList.size());
    assertEquals(jobDTO, jobDTOS.get(0));
  }
}
