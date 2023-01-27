package et.hrms.service.impl;

import et.hrms.dal.dto.FamilyDTO;
import et.hrms.dal.mapping.FamilyMapper;
import et.hrms.dal.model.Family;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.dal.repository.FamilyRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {


    private final FamilyRepository familyRepository;


    private final FamilyMapper familyMapper;
    private final EmployeeRepository employeeRepository;


    @Override
    public void createEmployeeFamily(Long employeeId, FamilyDTO familyDTO) {
        var family = familyMapper.toFamily(familyDTO);
        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    return new EntityNotFoundException(MessageFormat.format("Employee is not found by this id: {0}", employeeId));
                });
        family.setEmployee(employee);
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
            familyDTO.setDateOfBirth(family.getDateBirth());
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


    @Override
    public List<FamilyDTO> getAllFamily(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Family> families = familyRepository.findAll(pageable);
        return families.stream()
                .map(familyMapper::toFamilyDTO)
                .toList();
    }
}
