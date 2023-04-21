package et.hrms.service.leave;

import et.hrms.dal.dto.leave.LeaveRequestDTO;
import et.hrms.dal.mapping.leave.LeaveRequestMapper;
import et.hrms.dal.model.employee.Employee;
import et.hrms.dal.model.leave.LeaveRequest;
import et.hrms.dal.repository.employee.EmployeeRepository;
import et.hrms.dal.repository.leave.LeaveRequestRepository;
import et.hrms.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class provides the implementation for the LeaveRequestService interface.
 * It contains methods to create, update, and retrieve leave requests for an employee.
 */
@RequiredArgsConstructor
@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
  private LeaveRequestRepository leaveRequestRepository;
  private EmployeeRepository employeeRepository;
  private LeaveRequestMapper leaveRequestMapper;

  /**
   * Creates a new leave request for the specified employee.
   * @param employeeId the ID of the employee
   * @param leaveRequestDTO the DTO representing the leave request
   */
  @Override
  public void createLeaveRequest(Long employeeId, LeaveRequestDTO leaveRequestDTO) {
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee information is not found by this ID: " + employeeId));

    LeaveRequest leaveRequest = leaveRequestMapper.toEntity(leaveRequestDTO);
    leaveRequest.setEmployee(employee);
    leaveRequest.setStatus(true);
    leaveRequestRepository.save(leaveRequest);

  }

  /**
   * Updates an existing leave request for the specified employee.
   * @param employeeId the ID of the employee
   * @param leaveRequestDTO the DTO representing the leave request
   */
  @Override
  public void updateLeaveRequest(Long employeeId, LeaveRequestDTO leaveRequestDTO) {
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee is not found by this ID: " + employeeId));
    LeaveRequest leaveRequest = leaveRequestMapper.toEntity(leaveRequestDTO);
    leaveRequest.setEmployee(employee);
    leaveRequestRepository.save(leaveRequest);

  }

  /**
   * Retrieves the leave request for the specified employee.
   * @param employeeId the ID of the employee
   * @return the DTO representing the leave request
   */
  @Override
  public LeaveRequestDTO getLeaveRequestByEmployeeId(Long employeeId) {
    LeaveRequest leaveRequest = leaveRequestRepository.getLeaveRequestByEmployeeId(employeeId);
    return leaveRequestMapper.toDTO(leaveRequest);
  }

  /**
   * Retrieves all leave requests for the specified employee and status.
   * @param employeeId the ID of the employee
   * @param status the status of the leave requests (true for approved, false for pending)
   * @return a list of DTOs representing the leave requests
   */
  @Override
  public List<LeaveRequestDTO> getLeaveRequestByStatusAndEmployeeId(Long employeeId, boolean status) {
    List<LeaveRequest> leaveRequest = leaveRequestRepository.getLeaveRequestByStatusAndEmployeeId(employeeId, status);
    return leaveRequestMapper.toDTOList(leaveRequest);
  }

  /**
   * Retrieves all leave requests.
   * @param employeeId the ID of the employee
   * @return a list of DTOs representing the leave requests
   */
  @Override
  public List<LeaveRequestDTO> getAllLeaveRequest(Long employeeId) {
    return leaveRequestMapper.toDTOList(leaveRequestRepository.findAll());
  }
}
