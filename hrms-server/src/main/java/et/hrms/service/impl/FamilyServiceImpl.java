package et.hrms.service.impl;

import et.hrms.dal.dto.FamilyDTO;
import et.hrms.dal.mapping.FamilyMapper;
import et.hrms.dal.model.Family;
import et.hrms.dal.repository.FamilyRepository;
import et.hrms.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {


    private final FamilyRepository familyRepository;


    private final FamilyMapper familyMapper;



    @Override
    public void createFamily(FamilyDTO familyDTO) {
        Family family = new Family();
        family.setFamilyFirstName(familyDTO.getFamilyFirstName());
        family.setFamilyLastName(familyDTO.getFamilyLastName());
        family.setGender(familyDTO.getGender());
        family.setDate_Birth(familyDTO.getDateOfBirth());
        family.setNationality(familyDTO.getNationality());

         familyRepository.save(family);


    }


    @Override
    public List<FamilyDTO> getAllFamilyList() {
        List<FamilyDTO> familyDTOS = new ArrayList<>();
        FamilyDTO familyDTO = new FamilyDTO();
        List<Family> families = familyRepository.findAll();
        for (Family family : families) {
            familyDTO.setFamilyFirstName(family.getFamilyFirstName());
            familyDTO.setFamilyLastName(family.getFamilyLastName());
            familyDTO.setGender(family.getGender());
            familyDTO.setNationality(family.getNationality());
            familyDTO.setDateOfBirth(family.getDate_Birth());
            familyDTOS.add(familyDTO);
        }

        return familyDTOS;
    }

    @Override
    public FamilyDTO getFamilyById(Long id) {

        Optional<Family> family = familyRepository.findById(id);
        FamilyDTO familyDTO = new FamilyDTO();
        if (family.isPresent())
            familyDTO = familyMapper.toFamilyDTO(family.get());

        return familyDTO;
    }
}
