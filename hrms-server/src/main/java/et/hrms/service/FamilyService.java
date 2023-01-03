package et.hrms.service;

import et.hrms.dal.dto.FamilyDTO;

import java.util.List;

public interface FamilyService {
    void createFamily(FamilyDTO familyDTO);

    List<FamilyDTO> getAllFamilyList();

    FamilyDTO getFamilyById(Long id);
}
