package et.hrms.service;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.dto.EmployeeDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeEducationManagementService {
    @Transactional
    void saveEmployeeWithEducation(EmployeeDTO employeeDTO, List<EducationDTO> educationDTOS);

    @Transactional
    void updateEmployeeWithEducations(EmployeeDTO employeeDTO, List<EducationDTO> educationDTOS);
}
