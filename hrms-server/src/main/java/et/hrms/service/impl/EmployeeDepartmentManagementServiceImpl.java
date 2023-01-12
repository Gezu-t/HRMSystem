package et.hrms.service.impl;


import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.model.Department;
import et.hrms.dal.model.Employee;
import et.hrms.dal.model.EmployeeDepartmentManagement;
import et.hrms.dal.repository.DepartmentRepository;
import et.hrms.dal.repository.EmployeeAddressManageRepository;
import et.hrms.dal.repository.EmployeeDepartmentManageRepository;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.service.EmployeeDepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeDepartmentManagementServiceImpl implements EmployeeDepartmentsService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    private final EmployeeDepartmentManageRepository employeeDepartmentManageRepository;
    private final EmployeeAddressManageRepository employeeAddressManageRepository;


    @Override
    @Transactional
    public void saveEmployeeWithDepartments(EmployeeDTO employeeDTO, List<DepartmentDTO> departmentDTOS) {
        // Save the employee
        Employee employee = new Employee();
        // map the employee DTO to the employee entity
        employeeRepository.save(employee);

        // Save the departments for the employee
        Set<EmployeeDepartmentManagement> employeeDepartments = new HashSet<>();
        departmentDTOS.forEach(departmentDTO -> {
            Department department = departmentRepository.findById(departmentDTO.getDepartmentId()).orElse(null);
            if (department != null) {
                EmployeeDepartmentManagement employeeDepartmentManagement = new EmployeeDepartmentManagement();
                employeeDepartmentManagement.setEmployee(employee);
                employeeDepartmentManagement.setDepartment(department);
                employeeDepartments.add(employeeDepartmentManagement);
            }
        });
        employee.setEmployeeDepartmentManagements(employeeDepartments);
        employeeRepository.save(employee);
    }


    @Override
    @Transactional
    public void updateEmployeeWithDepartments(EmployeeDTO employeeDTO, List<DepartmentDTO> departmentDTOS) {
        // Retrieve the employee from the database
        Employee employee = employeeRepository.findById(employeeDTO.getEmployeeId()).orElse(null);
        if (employee == null) {
            return;
        }

        // Update the employee details
        // map the employee DTO to the employee entity
        employeeRepository.save(employee);

        // Update the departments for the employee
        Set<EmployeeDepartmentManagement> currentEmployeeDepartments = employee.getEmployeeDepartmentManagements();
        Set<EmployeeDepartmentManagement> newEmployeeDepartments = new HashSet<>();

        // Add any new departments
        departmentDTOS.forEach(departmentDTO -> {
            Department department = departmentRepository.findById(departmentDTO.getDepartmentId()).orElse(null);
            if (department != null && !currentEmployeeDepartments.contains(department)) {
                EmployeeDepartmentManagement employeeDepartment = new EmployeeDepartmentManagement();
                employeeDepartment.setEmployee(employee);
                employeeDepartment.setDepartment(department);
                newEmployeeDepartments.add(employeeDepartment);
            }
        });

        // Remove any old departments
        currentEmployeeDepartments.forEach(employeeDepartment -> {
            if (!departmentDTOS.contains(employeeDepartment.getDepartment())) {
                employeeDepartment.setEmployee(null);
                employeeDepartment.setDepartment(null);
                employeeDepartmentManageRepository.delete(employeeDepartment);
            }
        });

        // Add the new departments to the employee
        currentEmployeeDepartments.addAll(newEmployeeDepartments);

        // Save the employee to persist the changes to the database
        employeeRepository.save(employee);
    }


    @Override
    public Set<EmployeeDepartmentManagement> getEmployeeDepartmentByEmployeeId(Long employeeId) {
        return employeeDepartmentManageRepository.findByEmployeeId(employeeId);
    }


}
