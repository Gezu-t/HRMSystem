package job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmsystem.employeeprofileservice.controller.job.JobControllerImpl;
import com.hrmsystem.employeeprofileservice.dal.dto.job.JobDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.JobMapper;
import com.hrmsystem.employeeprofileservice.service.job.JobService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class JobControllerTest {


  @Mock
  private JobService jobService;

  @InjectMocks
  private JobControllerImpl jobController;
  private MockMvc mockMvc;

  private JobMapper jobMapper;

  private ObjectMapper objectMapper;

  @Before
  public void setUp(){
    mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testSaveJob() throws Exception {
    JobDTO jobDTO = new JobDTO();
    jobDTO.setJobId(1L);
    jobDTO.setTitle("Software development");
    jobDTO.setMaxSalary(1000);
    jobDTO.setMinSalary(500);
    jobDTO.setDescription("Added a new job description");

    String jsonRequest = objectMapper.writeValueAsString(jobDTO);

    mockMvc.perform(post("/api/jobs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest))
            .andExpect(status().isCreated());
  }

}
