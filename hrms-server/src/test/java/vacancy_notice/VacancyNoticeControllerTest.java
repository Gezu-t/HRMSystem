package vacancy_notice;

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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
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

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(vacancyNoticeController).build();
  }

  @Test
  public void testGetAllVacancyNotices() throws Exception {
    VacancyNoticeDTO vacancyNotice1 = new VacancyNoticeDTO();
    vacancyNotice1.setId(1L);
    vacancyNotice1.setJobTitle("Job Title 1");

    VacancyNoticeDTO vacancyNotice2 = new VacancyNoticeDTO();
    vacancyNotice2.setId(2L);
    vacancyNotice2.setJobTitle("Job Title 2");

    List<VacancyNoticeDTO> vacancyNotices = Arrays.asList(vacancyNotice1, vacancyNotice2);

    when(vacancyNoticeService.findAll()).thenReturn(vacancyNotices);

    mockMvc.perform(get("/api/vacancy-notices"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].jobTitle", is("Job Title 1")))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].jobTitle", is("Job Title 2")));

    verify(vacancyNoticeService, times(1)).findAll();
  }

  // other test cases for the VacancyNoticeController class
}