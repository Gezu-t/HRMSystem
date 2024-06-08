package et.hrms.service.leave.impl;

import et.hrms.client.employee.EmployeeClientService;
import et.hrms.dal.dto.leave.LeaveBalanceDTO;
import et.hrms.dal.mapper.leave.LeaveBalanceMapper;
import et.hrms.dal.model.leave.LeaveBalance;
import et.hrms.dal.repository.leave.LeaveBalanceRepository;
import et.hrms.service.leave.LeaveBalanceService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

    private static final Logger logger = LoggerFactory.getLogger(LeaveBalanceServiceImpl.class);
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final EmployeeClientService employeeClientService;
    private final LeaveBalanceMapper leaveBalanceMapper;

    public LeaveBalanceServiceImpl(LeaveBalanceRepository leaveBalanceRepository, EmployeeClientService employeeClientService, LeaveBalanceMapper leaveBalanceMapper) {
        this.leaveBalanceRepository = leaveBalanceRepository;
        this.employeeClientService = employeeClientService;
        this.leaveBalanceMapper = leaveBalanceMapper;
    }

    @Override
    public LeaveBalanceDTO createLeaveBalance(LeaveBalanceDTO leaveBalanceDTO) {
        logger.info("Creating new leave balance for employee ID: {}", leaveBalanceDTO.getEmployeeId());
        validateEmployeeExists(leaveBalanceDTO.getEmployeeId());

        LeaveBalance leaveBalance = leaveBalanceMapper.toEntity(leaveBalanceDTO);
        leaveBalance = leaveBalanceRepository.save(leaveBalance);
        return leaveBalanceMapper.toDTO(leaveBalance);
    }

    @Override
    public LeaveBalanceDTO updateLeaveBalance(Long id, LeaveBalanceDTO leaveBalanceDTO) {
        logger.info("Updating leave balance ID: {}", id);
        LeaveBalance leaveBalance = leaveBalanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leave balance not found for ID: " + id));

        leaveBalanceMapper.updateEntityFromDto(leaveBalanceDTO, leaveBalance);
        leaveBalance = leaveBalanceRepository.save(leaveBalance);
        return leaveBalanceMapper.toDTO(leaveBalance);
    }

    @Override
    public LeaveBalanceDTO getLeaveBalanceById(Long id) {
        logger.info("Fetching leave balance by ID: {}", id);
        LeaveBalance leaveBalance = leaveBalanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leave balance not found for ID: " + id));
        return leaveBalanceMapper.toDTO(leaveBalance);
    }

    @Override
    public List<LeaveBalanceDTO> getAllLeaveBalances() {
        logger.info("Fetching all leave balances");
        List<LeaveBalance> leaveBalances = leaveBalanceRepository.findAll();
        return leaveBalances.stream().map(leaveBalanceMapper::toDTO).collect(Collectors.toList());
    }

    private void validateEmployeeExists(Long employeeId) {
        try {
            employeeClientService.getEmployeeById(employeeId);
        } catch (EntityNotFoundException e) {
            logger.error("Employee validation failed, employee not found: {}", employeeId);
            throw new IllegalStateException("Employee with ID " + employeeId + " does not exist", e);
        }
    }
}
