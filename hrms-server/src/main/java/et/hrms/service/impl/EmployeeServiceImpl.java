package et.hrms.service.impl;

import et.hrms.dal.dto.*;
import et.hrms.dal.mapping.AppearanceMapper;
import et.hrms.dal.mapping.EmployeeMapper;
import et.hrms.dal.mapping.FamilyMapper;
import et.hrms.dal.model.*;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.service.EmployeeAddressManagementService;
import et.hrms.service.EmployeeDepartmentsService;
import et.hrms.service.EmployeeEducationManagementService;
import et.hrms.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final FamilyMapper familyMapper;
    private final EmployeeMapper employeeMapper;
    private final AppearanceMapper appearanceMapper;
    private final EmployeeRepository employeeRepository;

    private final EmployeeEducationManagementService employeeEducationManagementService;

    private final EmployeeDepartmentsService employeeDepartmentsService;

    private final EmployeeAddressManagementService employeeAddressManagementService;


    @Override
    @Transactional
    public void createEmployee(EmployeeDTO employeeDTO,
                               FamilyDTO familyDTO,
                               AppearanceDTO appearanceDTO,
                               List<DepartmentDTO> departmentDTOS,
                               List<AddressDTO> addressDTOS,
                               List<EducationDTO> educationDTOS) {
        var employee = employeeMapper.toEmployee(employeeDTO);

        Family family = familyMapper.toFamily(familyDTO);
        employee.setFamily(family);

        Appearance appearance = appearanceMapper.toAppearance(appearanceDTO);
        employee.setAppearance(appearance);

        employeeEducationManagementService.saveEmployeeWithEducation(employeeDTO, educationDTOS);
        employeeAddressManagementService.saveEmployeeWithAddress(employeeDTO, addressDTOS);
        employeeDepartmentsService.saveEmployeeWithDepartments(employeeDTO, departmentDTOS);


        employeeRepository.save(employee);


    }


    @Override
    public List<EmployeeDTO> getListOfEmployee() {

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        for (Employee employee : employees) {
            employeeDTO.setEmployeeId(employee.getId());
            employeeDTO.setEmployeeNo(employee.getEmployeeNumber());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setDateOfBirth(employee.getDateOfBirth());
            employeeDTO.setDateOfJoining(employee.getDateOfJoining());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;

    }


    @Override
    public List<DepartmentDTO> getDepartmentsForEmployee(Long employeeId) {
        // Retrieve the employee from the database
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            return Collections.emptyList();
        }

        // Retrieve the departments for the employee
        Set<EmployeeDepartmentManagement> departments = employee.getEmployeeDepartmentManagements();

        // Convert the departments to department DTOs and return the list
        return departments.stream().map(department -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setDepartmentId(department.getId());
            departmentDTO.setDepartmentName(department.getDepartment().getDepartmentName());
            return departmentDTO;
        }).toList();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Set<EmployeeDepartmentManagement> department = employeeDepartmentsService.getEmployeeDepartmentByEmployeeId(id);
        employee.setEmployeeDepartmentManagements(department);
        Set<EmployeeAddressManagement> employeeAddress = employeeAddressManagementService.getEmployeeAddressByEmployeeId(id);
        employee.setEmployeeAddressManagements(employeeAddress);
        return employeeMapper.toEmployeeDTO(employee);
    }


    @Override
    public EmployeeDTO getEmployeeByEmployeeNo(String employeeNo) {

        Employee employee = employeeRepository.findByEmployeeNumber(employeeNo);

        return employeeMapper.toEmployeeDTO(employee);

    }


}
