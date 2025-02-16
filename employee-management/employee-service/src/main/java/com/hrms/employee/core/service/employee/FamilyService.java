package com.hrms.employee.core.service.employee;

import com.hrms.employee.core.dal.dto.employee.FamilyDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface FamilyService {


    FamilyDTO createEmployeeFamily(Long employeeId, FamilyDTO familyDTO);

    List<FamilyDTO> getAllFamilyList();

    FamilyDTO getFamilyById(Long id);

    List<FamilyDTO> getAllFamily(int page, int size, Sort sort);
}
