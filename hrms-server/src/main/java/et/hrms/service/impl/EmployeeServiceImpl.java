package et.hrms.service.impl;

import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.dto.EmployeeAddressDTO;
import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.dto.EmployeeDetailDTO;
import et.hrms.dal.mapping.DepartmentMapper;
import et.hrms.dal.mapping.EmployeeAddressMapper;
import et.hrms.dal.mapping.EmployeeDetailMapper;
import et.hrms.dal.mapping.EmployeeMapper;
import et.hrms.dal.model.*;
import et.hrms.dal.repository.DepartmentRepository;
import et.hrms.dal.repository.EmployeeAddressRepository;
import et.hrms.dal.repository.EmployeeDetailRepository;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private final EmployeeAddressMapper employeeAddressMapper;
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
        List<EmployeeDetailDTO> employeeDetailDTOS = new ArrayList<>();
        for(EmployeeDetail employeeDetail: employeeDetails){
            EmployeeDetailDTO employeeDetailDTO = new EmployeeDetailDTO();
            EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employeeDetail.getEmployee());
            DepartmentDTO departmentDTO = departmentMapper.toDepartmentDTO(employeeDetail.getDepartment());
            employeeDetailDTO.setDepartmentDTO(departmentDTO);
            employeeDetailDTO.setEmployeeDTO(employeeDTO);
            employeeDetailDTOS.add(employeeDetailDTO);
        }
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();


        return employeeDTOS;
    }


    @Override
    public List<EmployeeDTO> getAllEmployeeList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.stream().map(employeeMapper::toEmployeeDTO)
                .toList();
    }


//    @Override
//    public EmployeeDTO getEmployeeById(Long id) {
//
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(EntityNotFoundException::new);
//        if (employee != null) {
//            List<DepartmentDTO> departments = departmentService.getDepartmentByEmployeeId(id);
//            if (departments != null) {
//                employee.setDepartments(departmentMapper.toDepartmentDTOs(departments));
//            } else {
//                throw new NullPointerException("Department is not found by this employee id:" + id);
//            }
//            Set<OrganizationAddressDTO> employeeAddress = addressService.getAddressByEmployeeId(id);
//            if (employeeAddress != null) {
//                employee.setAddresses(addressMapper.toAddressDTOset(employeeAddress));
//            } else {
//                throw new NullPointerException("OrganizationAddress is not found by this employee id:" + id);
//            }
//            return employeeMapper.toEmployeeDTO(employee);
//        } else {
//            throw new NullPointerException("Employee is not found by this id:" + id);
//        }
//    }
//
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
