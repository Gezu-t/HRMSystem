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
        var employeeInfo = new AtomicReference<>(new Employee());
        var employee = employeeMapper.toEmployee(employeeDTO);

        if (employee.getId() == null && employeeDTO != null) {

            List<EmployeeDetail> employeeDetails = new ArrayList<>();
            for (EmployeeDetailDTO employeeDetailDTO : employeeDTO.getEmployeeDetailDTOS()) {
                var employeeDetail = employeeDetailMapper.toEmployeeDetail(employeeDetailDTO);
                if (employeeDetail != null) {
                    var department = departmentRepository.findById(employeeDetailDTO.getDepartmentDTO().getDepartmentId())
                            .orElseThrow(() -> new EntityNotFoundException("Department information is not found"));
                    employeeDetail.setDepartment(department);
                } else {
                    throw new EntityNotFoundException("Department information does not exist");
                }
                employeeDetail.setEmployee(employee);
                employeeDetails.add(employeeDetail);
            }

            List<EmployeeAddress> employeeAddresses = new ArrayList<>();
            for (EmployeeAddressDTO addressDTO : employeeDTO.getEmployeeAddressDTOS()) {
                var address = addressMapper.toEmployeeAddress(addressDTO);
                address.setEmployee(employee);
                employeeAddresses.add(address);
            }
            employee.setEmployeeDetails(employeeDetails);
            employee.setEmployeeAddresses(employeeAddresses);
            if (!employee.getEmployeeAddresses().isEmpty() && !employee.getEmployeeDetails().isEmpty()) {
                employeeInfo.set(employeeRepository.save(employee));
                employeeAddressRepository.saveAll(employeeAddresses);
                employeeDetailRepository.saveAll(employeeDetails);
            }

        } else {
            throw new EntityNotFoundException("Employee has some problem persisting");
        }
        return employeeMapper.toEmployeeDTO(employeeInfo.get());
    }


    @Override
    public List<EmployeeDTO> findEmployeeByDepartmentName(String departmentName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<EmployeeDetail> employeeDetails = employeeDetailRepository.findEmployeeByDepartmentName(departmentName, pageable);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (EmployeeDetail employeeDetail : employeeDetails) {
            EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employeeDetail.getEmployee());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.stream().map(employeeMapper::toEmployeeDTO)
                .toList();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        Employee employee = employeeOptional.get();
        List<EmployeeDetailDTO> employeeDetailDTOS = new ArrayList<>();
        for (EmployeeDetail employeeDetail : employee.getEmployeeDetails()) {

            Department department = employeeDetail.getDepartment();
            DepartmentDTO departmentDTO = departmentMapper.toDepartmentDTO(department);
            EmployeeDetailDTO employeeDetailDTO = employeeDetailMapper.toEmployeeDetailDTO(employeeDetail);
            employeeDetailDTO.setDepartmentDTO(departmentDTO);

            employeeDetailDTOS.add(employeeDetailDTO);
        }
        EmployeeAppearance employeeAppearance = employee.getEmployeeAppearance();
        EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employee);
        List<EmployeeAddressDTO> employeeAddressDTOS = new ArrayList<>();
        for(EmployeeAddress employeeAddress: employee.getEmployeeAddresses()){
            employeeAddressDTOS.add(EmployeeAddressMapper.INSTANCE.toEmployeeAddressDTO(employeeAddress));
        }
        employeeDTO.setEmployeeDetailDTOS(employeeDetailDTOS);
        employeeDTO.setEmployeeAddressDTOS(employeeAddressDTOS);
        return employeeDTO;
    }


//
//    @Override
//    public EmployeeDTO getEmployeeByEmployeeNo(String employeeNo) {
//
//        Employee employee = employeeRepository.findByEmployeeNumber(employeeNo);
//
//        if (employee != null) {
//            List<DepartmentDTO> departments = departmentService.getDepartmentByEmployeeId(employee.getId());
//            if (departments != null) {
//                employee.setDepartments(departmentMapper.toDepartmentDTOs(departments));
//            }
//            Set<OrganizationAddressDTO> employeeAddress = addressService.getAddressByEmployeeId(employee.getId());
//            if (employeeAddress != null) {
//                employee.setAddresses(addressMapper.toAddressDTOset(employeeAddress));
//            }
//            return employeeMapper.toEmployeeDTO(employee);
//        } else {
//            throw new EntityNotFoundException("Employee is not found by Employee Number" + employeeNo);
//        }
//    }


}
