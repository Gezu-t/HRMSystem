package et.hrms.service;

import et.hrms.dal.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {

    @Transactional
    void createEmployee(EmployeeDTO employeeDTO,
                        FamilyDTO familyDTO,
                        AppearanceDTO appearanceDTO,
                        List<DepartmentDTO> departmentDTOS,
                        List<AddressDTO> addressDTOS);

    List<EmployeeDTO> getListOfEmployee();
}
