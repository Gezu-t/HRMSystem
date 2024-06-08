package leave;

import et.hrms.client.employee.EmployeeClientService;
import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.dal.dto.leave.LeaveBalanceDTO;
import et.hrms.dal.mapper.leave.LeaveBalanceMapper;
import et.hrms.dal.model.leave.LeaveBalance;
import et.hrms.dal.repository.leave.LeaveBalanceRepository;
import et.hrms.service.leave.impl.LeaveBalanceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LeaveBalanceServiceTest {

    @Mock
    private LeaveBalanceRepository leaveBalanceRepository;

    @Mock
    private EmployeeClientService employeeClientService;

    @Mock
    private LeaveBalanceMapper leaveBalanceMapper;

    @InjectMocks
    private LeaveBalanceServiceImpl leaveBalanceService;

    private LeaveBalanceDTO leaveBalanceDTO;
    private LeaveBalance leaveBalance;

    @BeforeEach
    void setUp() {
        leaveBalanceDTO = new LeaveBalanceDTO(1L, "5", 2, 20, 1L, true);
        leaveBalance = new LeaveBalance();
        leaveBalance.setId(1L);

    }

    @Test
    void testCreateLeaveBalance() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setName("John Doe");
        employeeDTO.setDepartment("HR");
        employeeDTO.setPosition("Manager");
        employeeDTO.setStatus("Active");

        when(employeeClientService.getEmployeeById(anyLong())).thenReturn(employeeDTO);
        when(leaveBalanceMapper.toEntity(any(LeaveBalanceDTO.class))).thenReturn(leaveBalance);
        when(leaveBalanceRepository.save(any(LeaveBalance.class))).thenReturn(leaveBalance);
        when(leaveBalanceMapper.toDTO(any(LeaveBalance.class))).thenReturn(leaveBalanceDTO);

        LeaveBalanceDTO result = leaveBalanceService.createLeaveBalance(leaveBalanceDTO);

        verify(leaveBalanceRepository).save(any(LeaveBalance.class));
        verify(leaveBalanceMapper).toDTO(any(LeaveBalance.class));
        assert result.equals(leaveBalanceDTO);
    }

    @Test
    void testGetLeaveBalanceById() {
        when(leaveBalanceRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(leaveBalance));
        when(leaveBalanceMapper.toDTO(any(LeaveBalance.class))).thenReturn(leaveBalanceDTO);

        LeaveBalanceDTO result = leaveBalanceService.getLeaveBalanceById(1L);

        verify(leaveBalanceRepository).findById(anyLong());
        assert result.equals(leaveBalanceDTO);
    }

    @Test
    void testUpdateLeaveBalance() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setName("John Doe");
        employeeDTO.setDepartment("HR");
        employeeDTO.setPosition("Manager");
        employeeDTO.setStatus("Active");
        lenient().when(leaveBalanceRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(leaveBalance));
        lenient().when(employeeClientService.getEmployeeById(anyLong())).thenReturn(employeeDTO);
        lenient().when(leaveBalanceRepository.save(any(LeaveBalance.class))).thenReturn(leaveBalance);
        lenient().when(leaveBalanceMapper.toDTO(any(LeaveBalance.class))).thenReturn(leaveBalanceDTO);

        LeaveBalanceDTO result = leaveBalanceService.updateLeaveBalance(1L, leaveBalanceDTO);

        // Assertions to ensure all expected methods are actually called
        verify(leaveBalanceRepository).save(any(LeaveBalance.class));
        verify(leaveBalanceMapper).toDTO(any(LeaveBalance.class));
        assert result.equals(leaveBalanceDTO);
    }

}
