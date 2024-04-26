package et.hrms.service.employee.impl;

import et.hrms.dal.dto.employee.FamilyDTO;
import et.hrms.dal.mapping.FamilyMapper;
import et.hrms.dal.model.employee.Family;
import et.hrms.dal.repository.employee.EmployeeRepository;
import et.hrms.dal.repository.employee.FamilyRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.employee.FamilyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final FamilyMapper familyMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public FamilyDTO createEmployeeFamily(Long employeeId, FamilyDTO familyDTO) {
        var family = familyMapper.toFamily(familyDTO);
        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Employee is not found by this id: {0}", employeeId)));
        family.setEmployee(employee);
        return familyMapper.toFamilyDTO(familyRepository.save(family));
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
        if (family.isPresent()) {
            familyDTO = familyMapper.toFamilyDTO(family.get());
            log.info("Family information is found");
        }else{
            log.info("Family information is not found");
        }
        return familyDTO;
    }


    @Override
    public List<FamilyDTO> getAllFamily(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Family> families = familyRepository.findAll(pageable);
        return families.stream()
                .map(familyMapper::toFamilyDTO)
                .toList();
    }
}
