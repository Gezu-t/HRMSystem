package et.hrms.service.impl;

import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.dto.EmployeeAddressDTO;
import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.dto.EmployeeDetailDTO;
import et.hrms.dal.mapping.DepartmentMapper;
import et.hrms.dal.mapping.EmployeeAddressMapper;
import et.hrms.dal.mapping.EmployeeDetailMapper;
import et.hrms.dal.mapping.EmployeeMapper;
import et.hrms.dal.model.Employee;
import et.hrms.dal.model.EmployeeAddress;
import et.hrms.dal.model.EmployeeDetail;
import et.hrms.dal.repository.DepartmentRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

            if (!employee.getEmployeeAddresses().isEmpty() && !employee.getEmployeeDetails().isEmpty()) {
                employee = employeeRepository.save(employee);
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
                .toList();
    }

    private List<EmployeeAddress> createEmployeeAddresses(Employee employee, List<EmployeeAddressDTO> employeeAddressDTOS) {
        return employeeAddressDTOS.stream()
                .map(addressDTO -> {
                    var address = addressMapper.toEmployeeAddress(addressDTO);
                    address.setEmployee(employee);
                    return address;
                })
                .toList();
    }

    @Override
    public List<EmployeeDTO> findEmployeeByDepartmentName(String departmentName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<EmployeeDetail> employeeDetails = employeeDetailRepository.findEmployeeByDepartmentName(departmentName, pageable);
        return employeeDetails.stream()
                .map(EmployeeDetail::getEmployee)
                .map(employeeMapper::toEmployeeDTO)
                .toList();
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.stream()
                .map(employeeMapper::toEmployeeDTO)
                .toList();
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
                .toList();

        List<EmployeeAddressDTO> employeeAddressDTOS = employee.getEmployeeAddresses().stream()
                .map(EmployeeAddressMapper.INSTANCE::toEmployeeAddressDTO)
                .toList();

        EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employee);
        employeeDTO.setEmployeeDetailDTOS(employeeDetailDTOS);
        employeeDTO.setEmployeeAddressDTOS(employeeAddressDTOS);

        return employeeDTO;
    }
}
