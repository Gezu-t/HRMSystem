package et.hrms.service.impl;

import et.hrms.dal.dto.*;
import et.hrms.dal.mapping.AddressMapper;
import et.hrms.dal.mapping.AppearanceMapper;
import et.hrms.dal.mapping.DepartmentMapper;
import et.hrms.dal.mapping.FamilyMapper;
import et.hrms.dal.model.*;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    private final EmployeeRepository employeeRepository;
    private final FamilyMapper familyMapper;
    private final DepartmentMapper departmentMapper;
    private final AddressMapper addressMapper;
    private final AppearanceMapper appearanceMapper;




    @Override
    @Transactional
    public void createEmployee(EmployeeDTO employeeDTO,
                               FamilyDTO familyDTO,
                               AppearanceDTO appearanceDTO,
                               List<DepartmentDTO> departmentDTOS,
                               List<AddressDTO> addressDTOS) {
        var employee = new Employee();
        employee.setEmployeeNumber(employeeDTO.getEmployeeNo());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setDateOfJoining(employeeDTO.getDateOfJoining());

        Family family = familyMapper.toFamily(familyDTO);
        employee.setFamily(family);

        Appearance appearance = appearanceMapper.toAppearance(appearanceDTO);
        employee.setAppearance(appearance);

        List<Department> departments = departmentMapper.toDepartmentList(departmentDTOS);
        var employeeDepartment = new EmployeeDepartmentManagement();
        Set<EmployeeDepartmentManagement> employeeDepartmentManagements = new HashSet<>();
        for(Department department: departments){
            employeeDepartment.setDepartment(department);
            employeeDepartmentManagements.add(employeeDepartment);
        }
        employee.setEmployeeDepartmentManagements(employeeDepartmentManagements);


        List<Address> addresses = addressMapper.toAddressList(addressDTOS);
        var employeeAddressManagement = new EmployeeAddressManagement();
        Set<EmployeeAddressManagement> employeeAddressManagements = new HashSet<>();
        for(Address address: addresses){
            employeeAddressManagement.setAddress(address);
            employeeAddressManagements.add(employeeAddressManagement);
        }
        employee.setEmployeeAddressManagements(employeeAddressManagements);

        if(employee.getEmployeeNumber().isEmpty()) {
            logger.error(employee.getEmployeeNumber().formatted("Employee number is:"));
        }

        employeeRepository.save(employeeRepository.save(employee));


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


}
