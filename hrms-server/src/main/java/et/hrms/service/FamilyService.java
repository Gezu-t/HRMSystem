package et.hrms.service;

import et.hrms.dal.dto.FamilyDTO;

import java.util.List;

public interface FamilyService {


    void createEmployeeFamily(Long employeeId, FamilyDTO familyDTO);

    List<FamilyDTO> getAllFamilyList();

    FamilyDTO getFamilyById(Long id);

    List<FamilyDTO> getAllFamily(int page, int size);
}
