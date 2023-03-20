package et.hrms.service.impl;

import et.hrms.dal.dto.*;
import et.hrms.dal.mapping.*;
import et.hrms.dal.model.*;
import et.hrms.dal.repository.DepartmentRepository;
import et.hrms.dal.repository.EmployeeAddressRepository;
import et.hrms.dal.repository.EmployeeDetailRepository;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.exceptions.EmployeeNotFoundException;
import et.hrms.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final EmployeeAddressMapper addressMapper;
    private final EmployeeDetailMapper employeeDetailMapper;
    private final DepartmentRepository departmentRepository;
    private final EmployeeAddressRepository employeeAddressRepository;
    private final EmployeeDetailRepository employeeDetailRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        var employee = employeeMapper.toEmployee(employeeDTO);

        if (employee.getId() == null && employeeDTO != null) {
            employee.setEmployeeDetails(createEmployeeDetails(employee, employeeDTO.getEmployeeDetailDTOS()));
            employee.setEmployeeAddresses(createEmployeeAddresses(employee, employeeDTO.getEmployeeAddressDTOS()));

            if (!employee.getEmployeeAddresses().isEmpty() && !employee.getEmployeeDetails().isEmpty()) {
                employee = employeeRepository.save(employee);
                employeeAddressRepository.saveAll(employee.getEmployeeAddresses());
                employeeDetailRepository.saveAll(employee.getEmployeeDetails());
            } else {
                throw new EntityNotFoundException("Employee has some problem persisting");
            }
        }

        return employeeMapper.toEmployeeDTO(employee);
    }

    private List<EmployeeDetail> createEmployeeDetails(Employee employee, List<EmployeeDetailDTO> employeeDetailDTOS) {
        return employeeDetailDTOS.stream()
                .map(employeeDetailDTO -> {
                    var employeeDetail = employeeDetailMapper.toEmployeeDetail(employeeDetailDTO);
                    var department = departmentRepository.findById(employeeDetailDTO.getDepartmentDTO().getDepartmentId())
                            .orElseThrow(() -> new EntityNotFoundException("Department information is not found"));
                    employeeDetail.setDepartment(department);
                    employeeDetail.setEmployee(employee);
                    return employeeDetail;
                })
                .collect(Collectors.toList());
    }

    private List<EmployeeAddress> createEmployeeAddresses(Employee employee, List<EmployeeAddressDTO> employeeAddressDTOS) {
        return employeeAddressDTOS.stream()
                .map(addressDTO -> {
                    var address = addressMapper.toEmployeeAddress(addressDTO);
                    address.setEmployee(employee);
                    return address;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findEmployeeByDepartmentName(String departmentName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<EmployeeDetail> employeeDetails = employeeDetailRepository.findEmployeeByDepartmentName(departmentName, pageable);
        return employeeDetails.stream()
                .map(EmployeeDetail::getEmployee)
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        List<EmployeeDetailDTO> employeeDetailDTOS = employee.getEmployeeDetails().stream()
                .map(employeeDetail -> {
                    DepartmentDTO departmentDTO = departmentMapper.toDepartmentDTO(employeeDetail.getDepartment());
                    EmployeeDetailDTO employeeDetailDTO = employeeDetailMapper.toEmployeeDetailDTO(employeeDetail);
                    employeeDetailDTO.setDepartmentDTO(departmentDTO);
                    return employeeDetailDTO;
                })
                .collect(Collectors.toList());

        List<EmployeeAddressDTO> employeeAddressDTOS = employee.getEmployeeAddresses().stream()
                .map(EmployeeAddressMapper.INSTANCE::toEmployeeAddressDTO)
                .collect(Collectors.toList());

        EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employee);
        employeeDTO.setEmployeeDetailDTOS(employeeDetailDTOS);
        employeeDTO.setEmployeeAddressDTOS(employeeAddressDTOS);

        return employeeDTO;
    }
}
