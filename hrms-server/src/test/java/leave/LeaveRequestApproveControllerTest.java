package leave;

import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.controller.leave.LeaveRequestApproveControllerImpl;
import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;
import et.hrms.dal.model.leave.LeaveRequestEvents;
import et.hrms.service.leave.LeaveRequestApproveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
public class LeaveRequestApproveControllerTest {

  @InjectMocks
  private LeaveRequestApproveControllerImpl leaveRequestApproveController;

  @Mock
  private LeaveRequestApproveService leaveRequestApproveService;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(leaveRequestApproveController).build();
    objectMapper = new ObjectMapper();

  }

  @Test
  public void testCreateLeaveRequestApprove() throws Exception {
    long employeeId = 1L;
    long leaveRequestId = 1L;
    LeaveRequestApproveDTO leaveRequestApproveDTO = new LeaveRequestApproveDTO();
    leaveRequestApproveDTO.setLeaveRequestApproveId(1L);
    leaveRequestApproveDTO.setApprovalUserComment("You can take the 1 day but not two day");
    leaveRequestApproveDTO.setLeaveRequestId(1L);
    leaveRequestApproveDTO.setApprovalUserId(1L);
    leaveRequestApproveDTO.setLeaveRequestEvents(LeaveRequestEvents.APPROVE);
    leaveRequestApproveDTO.setDescription("Approved a new leave request");
    leaveRequestApproveDTO.setStatus(true);
    Mockito.doNothing().when(leaveRequestApproveService).creatLeaveRequestApprove( anyLong(), anyLong(), any(LeaveRequestApproveDTO.class));

    mockMvc.perform(post("/api/leaveRequestApproves/{employeeId}/{leaveRequestId}", employeeId, leaveRequestId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(leaveRequestApproveDTO)))
            .andExpect(status().isCreated());
  }


}
