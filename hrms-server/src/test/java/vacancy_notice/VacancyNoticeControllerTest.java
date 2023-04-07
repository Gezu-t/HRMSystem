package vacancy_notice;

import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.controller.impl.VacancyNoticeControllerImpl;
import et.hrms.dal.dto.VacancyNoticeDTO;
import et.hrms.service.VacancyNoticeService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class VacancyNoticeControllerTest {

  @Mock
  private VacancyNoticeService vacancyNoticeService;

  @InjectMocks
  private VacancyNoticeControllerImpl vacancyNoticeController;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();
  private VacancyNoticeDTO vacancyNotice1;
  private VacancyNoticeDTO VacancyNoticeDTO2;
  private  List<VacancyNoticeDTO> vacancyNotices;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(vacancyNoticeController).build();
    vacancyNotice1 = new VacancyNoticeDTO();
    vacancyNotice1.setId(1L);
    vacancyNotice1.setJobTitle("Job Title 1");

    VacancyNoticeDTO2 = new VacancyNoticeDTO();
    VacancyNoticeDTO2.setId(2L);
    VacancyNoticeDTO2.setJobTitle("Job Title 2");

    vacancyNotices = Arrays.asList(vacancyNotice1, VacancyNoticeDTO2);

  }

  @Test
  public void testGetAllVacancyNotices() throws Exception {

    // Set up the mocks
    when(vacancyNoticeService.findAll()).thenReturn(List.of(vacancyNotice1));

    // Call the getAllBranchInformation endpoint
    mockMvc.perform(get("/api/vacancy-notices"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].jobTitle").value(vacancyNotice1.getJobTitle()));

  }

  // other test cases for the VacancyNoticeController class
}