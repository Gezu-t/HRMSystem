package et.hrms.service.impl;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.model.*;
import et.hrms.dal.repository.EducationRepository;
import et.hrms.dal.repository.EmployeeEducationManageRepository;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.service.EmployeeEducationManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class EmployeeEducationManagementServiceImpl implements EmployeeEducationManagementService {

    private final EmployeeEducationManageRepository employeeEducationManageRepository;

    private final EmployeeRepository employeeRepository;

    private final EducationRepository educationRepository;


    @Override
    @Transactional
    public void saveEmployeeWithEducation(EmployeeDTO employeeDTO, List<EducationDTO> educationDTOS) {

        // Save the employee
        Employee employee = new Employee();

        // Save the educations for the employee
        Set<EmployeeEducationManagement> employeeEducationManagements = new HashSet<>();
        educationDTOS.forEach(educationDTO -> {
            Education education = educationRepository.findById(educationDTO.getEducationId()).orElse(null);
            if (education != null) {
                EmployeeEducationManagement employeeEducationManagement = new EmployeeEducationManagement();
                employeeEducationManagement.setEmployee(employee);
                employeeEducationManagement.setEducation(education);
                employeeEducationManagements.add(employeeEducationManagement);
            }
        });
        employee.setEmployeeEducationManagements(employeeEducationManagements);
        employeeRepository.save(employee);

    }


    @Override
    @Transactional
    public void updateEmployeeWithEducations(EmployeeDTO employeeDTO, List<EducationDTO> educationDTOS) {
        // Retrieve the employee from the database
        Employee employee = employeeRepository.findById(employeeDTO.getEmployeeId()).orElse(null);
        if (employee == null) {
            return;
        }

        // Update the employee details
        // map the employee DTO to the employee entity
        employeeRepository.save(employee);

        // Update the departments for the employee
        Set<EmployeeEducationManagement> currentEmployeeEducations = employee.getEmployeeEducationManagements();
        Set<EmployeeEducationManagement> newEmployeeEducations = new HashSet<>();

        // Add any new educations
        educationDTOS.forEach(educationDTO -> {
            Education education = educationRepository.findById(educationDTO.getEducationId()).orElse(null);
            if ((education != null) && !currentEmployeeEducations.contains(education)) {
                EmployeeEducationManagement educationManagement = new EmployeeEducationManagement();
                educationManagement.setEmployee(employee);
                educationManagement.setEducation(education);
                newEmployeeEducations.add(educationManagement);
            }
        });

        // Remove any old educations
        currentEmployeeEducations.forEach(educationManagement -> {
            if (!educationDTOS.contains(educationManagement.getEducation())) {
                educationManagement.setEmployee(null);
                educationManagement.setEducation(null);
                employeeEducationManageRepository.delete(educationManagement); // not
            }
        });

        // Add the new educations to the employee
        currentEmployeeEducations.addAll(newEmployeeEducations);

        // Save the employee to persist the changes to the database
        employeeRepository.save(employee);
    }


}
