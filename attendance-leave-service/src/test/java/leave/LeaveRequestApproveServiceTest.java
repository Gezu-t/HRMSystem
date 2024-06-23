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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LeaveRequestApproveServiceTest {

    @Mock
    private LeaveRequestApproveRepository leaveRequestApproveRepository;

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @Mock
    private EmployeeClientService employeeClientService;

    @Mock
    private LeaveRequestApproveMapper leaveRequestApproveMapper;

    @InjectMocks
    private LeaveRequestApproveServiceImpl leaveRequestApproveService;

    private EmployeeDTO activeEmployee;
    private EmployeeDTO inactiveEmployee;
    private LeaveRequestApproveDTO leaveRequestApproveDTO;
    private LeaveRequest leaveRequest;

    @Before
    public void setUp() {
        leaveRequestApproveDTO = new LeaveRequestApproveDTO();
        leaveRequestApproveDTO.setLeaveRequestApproveId(1L);
        leaveRequestApproveDTO.setRequestApproveDate(LocalDate.now());
        leaveRequestApproveDTO.setApprovalUserId(1L);
        leaveRequestApproveDTO.setLeaveRequestId(1L);
        leaveRequestApproveDTO.setLeaveRequestEvents(LeaveRequestEvents.APPROVE);
        leaveRequestApproveDTO.setStatus(true);
        leaveRequestApproveDTO.setApprovalUserComment("Approved for good performance");
        leaveRequestApproveDTO.setDescription("All conditions met for approval");

        // Enhanced employee DTO
        activeEmployee = new EmployeeDTO();
        activeEmployee.setId(1L);
        activeEmployee.setName("John Doe");
        activeEmployee.setDepartment("IT");
        activeEmployee.setPosition("Developer");
        activeEmployee.setStatus("Active");

        inactiveEmployee = new EmployeeDTO();
        inactiveEmployee.setId(1L);
        inactiveEmployee.setName("John Doe");
        inactiveEmployee.setDepartment("IT");
        inactiveEmployee.setPosition("Developer");
        inactiveEmployee.setStatus("InActive");

        leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        Mockito.lenient().when(employeeClientService.getEmployeeById(1L)).thenReturn(inactiveEmployee);
        Mockito.lenient().when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(leaveRequest));
        // Other setup code
    }

    @Test
    public void shouldApproveLeaveWhenEmployeeIsActive() {
        when(employeeClientService.getEmployeeById(1L)).thenReturn(activeEmployee);
        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(leaveRequest));
        when(leaveRequestApproveMapper.toEntity(any(LeaveRequestApproveDTO.class))).thenReturn(new LeaveRequestApprove());
        when(leaveRequestApproveRepository.save(any(LeaveRequestApprove.class))).thenReturn(new LeaveRequestApprove());

        leaveRequestApproveService.createLeaveRequestApprove(1L, 1L, leaveRequestApproveDTO);

        verify(leaveRequestApproveRepository).save(any(LeaveRequestApprove.class));
    }



    @Test
    public void shouldThrowExceptionWhenEmployeeIsNotActive() {
        when(employeeClientService.getEmployeeById(1L)).thenReturn(inactiveEmployee);
        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(leaveRequest));

        Exception exception = assertThrows(RuntimeException.class, () ->
                leaveRequestApproveService.createLeaveRequestApprove(1L, 1L, leaveRequestApproveDTO)
        );

        assertEquals("Employee status is not active for approval", exception.getMessage());
    }


    @Test
    public void shouldThrowExceptionWhenLeaveRequestNotFound() {
        when(employeeClientService.getEmployeeById(1L)).thenReturn(activeEmployee);
        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                leaveRequestApproveService.createLeaveRequestApprove(1L, 1L, leaveRequestApproveDTO)
        );

        assertEquals("Leave request not found: 1", exception.getMessage());
    }


}
