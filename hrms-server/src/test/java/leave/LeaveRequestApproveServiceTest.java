package leave;

import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;
import et.hrms.dal.mapping.leave.LeaveRequestApproveMapper;
import et.hrms.dal.model.employee.Employee;
import et.hrms.dal.model.leave.LeaveRequest;
import et.hrms.dal.model.leave.LeaveRequestApprove;
import et.hrms.dal.model.leave.LeaveRequestEvents;
import et.hrms.dal.repository.employee.EmployeeRepository;
import et.hrms.dal.repository.leave.LeaveRequestApproveRepository;
import et.hrms.dal.repository.leave.LeaveRequestRepository;
import et.hrms.service.leave.LeaveRequestApproveServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
  private EmployeeRepository employeeRepository;

  @Mock
  private LeaveRequestApproveMapper leaveRequestApproveMapper;

  private LeaveRequestApproveDTO leaveRequestApproveDTO;
  private LeaveRequestApprove leaveRequestApprove;


  @Test
  public void testCreateLeaveRequestApprove() {
    Long leaveRequestId = 1L;
    LeaveRequest leaveRequest = new LeaveRequest();
    leaveRequest.setId(1L);

    Employee employee = new Employee();
    employee.setId(1L);
    leaveRequest.setEmployee(employee);

    LeaveRequestApprove leaveRequestApprove = new LeaveRequestApprove();
    leaveRequestApprove.setId(1L);
    leaveRequestApprove.setLeaveRequestEvents(LeaveRequestEvents.APPROVE);
    LeaveRequestApproveDTO leaveRequestApproveDTO = new LeaveRequestApproveDTO();
    leaveRequestApproveDTO.setLeaveRequestApproveId(1L);

    when(leaveRequestRepository.findById(leaveRequestId)).thenReturn(Optional.of(leaveRequest));
    when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
    when(leaveRequestApproveMapper.toEntity(leaveRequestApproveDTO)).thenReturn(leaveRequestApprove);

    leaveRequestApproveService.creatLeaveRequestApprove(1L, 1L, leaveRequestApproveDTO);

    verify(leaveRequestRepository, times(1)).findById(anyLong());
    verify(employeeRepository, times(1)).findById(anyLong());
    verify(leaveRequestApproveMapper, times(1)).toEntity(any(LeaveRequestApproveDTO.class));
    verify(leaveRequestApproveRepository, times(1)).save(any(LeaveRequestApprove.class));
  }
}
