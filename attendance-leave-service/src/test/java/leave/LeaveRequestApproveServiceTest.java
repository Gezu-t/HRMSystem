package leave;

import et.hrms.client.employee.EmployeeClientService;
import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;
import et.hrms.dal.mapper.leave.LeaveRequestApproveMapper;
import et.hrms.dal.model.leave.LeaveRequest;
import et.hrms.dal.model.leave.LeaveRequestApprove;
import et.hrms.dal.model.leave.LeaveRequestEvents;
import et.hrms.dal.repository.leave.LeaveRequestApproveRepository;
import et.hrms.dal.repository.leave.LeaveRequestRepository;
import et.hrms.service.leave.impl.LeaveRequestApproveServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
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

    List<EmployeeDTO> employee = new ArrayList<>();
    EmployeeDTO employeeDTO = new EmployeeDTO();
    employeeDTO.setId(employeeId);
    employee.add(employeeDTO);

    LeaveRequestApprove leaveRequestApprove = new LeaveRequestApprove();
    leaveRequestApprove.setId(1L);
    leaveRequestApprove.setLeaveRequestEvents(LeaveRequestEvents.APPROVE);
    LeaveRequestApproveDTO leaveRequestApproveDTO = new LeaveRequestApproveDTO();
    leaveRequestApproveDTO.setLeaveRequestApproveId(1L);

    when(leaveRequestRepository.findById(leaveRequestId)).thenReturn(Optional.of(leaveRequest));
    when(leaveRequestApproveMapper.toEntity(leaveRequestApproveDTO)).thenReturn(leaveRequestApprove);

    leaveRequestApproveService.createLeaveRequestApprove(employeeId, leaveRequestId, leaveRequestApproveDTO);

    verify(leaveRequestRepository, times(1)).findById(leaveRequestId);
    verify(leaveRequestApproveMapper, times(1)).toEntity(leaveRequestApproveDTO);
    verify(leaveRequestApproveRepository, times(1)).save(leaveRequestApprove);
  }

//  @Test
//  public void testGetAllEmployees() {
//    // Setup
//    EmployeeDTO[] mockEmployees = {new EmployeeDTO(1L, "John Doe"), new EmployeeDTO(2L, "Jane Doe")};
//    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(EmployeeDTO[].class)))
//            .thenReturn(mockEmployees);
//
//    // Execution
//    EmployeeDTO[] employees = employeeClientService.getAllEmployees();
//
//    // Assertion
//    Mockito.verify(restTemplate).getForObject(employeeServiceUrl + "/employees", EmployeeDTO[].class);
//    assertEquals("John Doe", employees[0].getName());
//    assertEquals(2, employees.length);
//  }



}
