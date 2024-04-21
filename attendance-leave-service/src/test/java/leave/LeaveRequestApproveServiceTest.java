package leave;

import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;
import et.hrms.dal.mapper.leave.LeaveRequestApproveMapper;
import et.hrms.dal.model.leave.LeaveRequest;
import et.hrms.dal.model.leave.LeaveRequestApprove;
import et.hrms.dal.model.leave.LeaveRequestEvents;
import et.hrms.dal.repository.leave.LeaveRequestApproveRepository;
import et.hrms.dal.repository.leave.LeaveRequestRepository;
import et.hrms.service.employee.EmployeeClientService;
import et.hrms.service.leave.impl.LeaveRequestApproveServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LeaveRequestApproveServiceTest {

  @InjectMocks
  private LeaveRequestApproveServiceImpl leaveRequestApproveService;

  @Mock
  private LeaveRequestApproveRepository leaveRequestApproveRepository;

  @Mock
  private LeaveRequestRepository leaveRequestRepository;

  @Mock
  private EmployeeClientService employeeClientService;

  @Mock
  private LeaveRequestApproveMapper leaveRequestApproveMapper;

  private LeaveRequestApproveDTO leaveRequestApproveDTO;
  private LeaveRequestApprove leaveRequestApprove;

  @Test
  public void testCreateLeaveRequestApprove() {
    Long leaveRequestId = 1L;
    Long employeeId = 1L;
    LeaveRequest leaveRequest = new LeaveRequest();
    leaveRequest.setId(leaveRequestId);

    EmployeeDTO employee = new EmployeeDTO();
    employee.setId(employeeId);

    LeaveRequestApprove leaveRequestApprove = new LeaveRequestApprove();
    leaveRequestApprove.setId(1L);
    leaveRequestApprove.setLeaveRequestEvents(LeaveRequestEvents.APPROVE);
    LeaveRequestApproveDTO leaveRequestApproveDTO = new LeaveRequestApproveDTO();
    leaveRequestApproveDTO.setLeaveRequestApproveId(1L);

    when(leaveRequestRepository.findById(leaveRequestId)).thenReturn(Optional.of(leaveRequest));
    when(employeeClientService.getEmployeeById(employeeId)).thenReturn(employee);
    when(leaveRequestApproveMapper.toEntity(leaveRequestApproveDTO)).thenReturn(leaveRequestApprove);

    leaveRequestApproveService.createLeaveRequestApprove(employeeId, leaveRequestId, leaveRequestApproveDTO);

    verify(leaveRequestRepository, times(1)).findById(leaveRequestId);
    verify(employeeClientService, times(1)).getEmployeeById(employeeId);
    verify(leaveRequestApproveMapper, times(1)).toEntity(leaveRequestApproveDTO);
    verify(leaveRequestApproveRepository, times(1)).save(leaveRequestApprove);
  }
}
