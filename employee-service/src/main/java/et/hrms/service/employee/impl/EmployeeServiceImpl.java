package et.hrms.service.employee.impl;

import et.hrms.dal.dto.employee.EmployeeAddressDTO;
import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.dal.dto.employee.EmployeeDetailDTO;
import et.hrms.dal.mapping.DepartmentMapper;
import et.hrms.dal.mapping.EmployeeAddressMapper;
import et.hrms.dal.mapping.EmployeeDetailMapper;
import et.hrms.dal.mapping.EmployeeMapper;
import et.hrms.dal.model.employee.Employee;
import et.hrms.dal.model.employee.EmployeeAddress;
import et.hrms.dal.model.employee.EmployeeDetail;
import et.hrms.dal.repository.employee.EmployeeDetailRepository;
import et.hrms.dal.repository.employee.EmployeeRepository;
import et.hrms.dal.repository.structure.DepartmentRepository;
import et.hrms.exceptions.EmployeeNotFoundException;
import et.hrms.service.employee.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final EmployeeAddressMapper addressMapper;
    private final EmployeeDetailMapper employeeDetailMapper;
    private final DepartmentRepository departmentRepository;
    private final EmployeeDetailRepository employeeDetailRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        var employee = employeeMapper.toEmployee(employeeDTO);

        if (employee.getId() == null && employeeDTO != null) {
            employee.setEmployeeDetails(createEmployeeDetails(employee, employeeDTO.getEmployeeDetailDTOS()));
            employee.setEmployeeAddresses(createEmployeeAddresses(employee, employeeDTO.getEmployeeAddressDTOS()));
            validateAndSaveEmployee(employee);
        }

        return employeeMapper.toEmployeeDTO(employee);
    }

    private void validateAndSaveEmployee(Employee employee) {
        if (!employee.getEmployeeAddresses().isEmpty() && !employee.getEmployeeDetails().isEmpty()) {
            employeeRepository.save(employee);
        } else {
            throw new EntityNotFoundException("Employee has some problem persisting");
        }
    }

    private List<EmployeeDetail> createEmployeeDetails(Employee employee, List<EmployeeDetailDTO> employeeDetailDTOS) {
        return employeeDetailDTOS.stream()
                .map(dto -> createEmployeeDetail(employee, dto))
                .collect(Collectors.toList());
    }

    private EmployeeDetail createEmployeeDetail(Employee employee, EmployeeDetailDTO dto) {
        var employeeDetail = employeeDetailMapper.toEmployeeDetail(dto);
        var department = departmentRepository.findById(dto.getDepartmentDTO().getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department information is not found"));
        employeeDetail.setDepartment(department);
        employeeDetail.setEmployee(employee);
        return employeeDetail;
    }

    private List<EmployeeAddress> createEmployeeAddresses(Employee employee, List<EmployeeAddressDTO> employeeAddressDTOS) {
        return employeeAddressDTOS.stream()
                .map(dto -> createEmployeeAddress(employee, dto))
                .collect(Collectors.toList());
    }

    private EmployeeAddress createEmployeeAddress(Employee employee, EmployeeAddressDTO dto) {
        var address = addressMapper.toEmployeeAddress(dto);
        address.setEmployee(employee);
        return address;
    }

    @Override
    public List<EmployeeDTO> findEmployeeByDepartmentName(String departmentName, int page, int size) {
        var pageable = PageRequest.of(page, size);
        var employeeDetails = employeeDetailRepository.findEmployeeByDepartmentName(departmentName, pageable);
        return employeeDetails.stream()
                .map(EmployeeDetail::getEmployee)
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployeeList(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var employees = employeeRepository.findAll(pageable);
        return employees.stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        return convertToEmployeeDTO(employee);
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        var employeeDetailDTOS = employee.getEmployeeDetails().stream()
                .map(this::convertToEmployeeDetailDTO)
                .collect(Collectors.toList());

        var employeeAddressDTOS = employee.getEmployeeAddresses().stream()
                .map(addressMapper::toEmployeeAddressDTO)
                .collect(Collectors.toList());

        var employeeDTO = employeeMapper.toEmployeeDTO(employee);
        employeeDTO.setEmployeeDetailDTOS(employeeDetailDTOS);
        employeeDTO.setEmployeeAddressDTOS(employeeAddressDTOS);

        return employeeDTO;
    }

    private EmployeeDetailDTO convertToEmployeeDetailDTO(EmployeeDetail employeeDetail) {
        var departmentDTO = departmentMapper.toDepartmentDTO(employeeDetail.getDepartment());
        var employeeDetailDTO = employeeDetailMapper.toEmployeeDetailDTO(employeeDetail);
        employeeDetailDTO.setDepartmentDTO(departmentDTO);
        return employeeDetailDTO;
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> searchEmployeesByName(String name) {
        List<Employee> employees = employeeRepository.searchByFirstNameOrLastName(name);
        return employees.stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

}
