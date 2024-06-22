package et.hrms.service.employee.impl;

import et.hrms.dal.dto.education.EducationDTO;
import et.hrms.dal.dto.employee.*;
import et.hrms.dal.mapping.department.DepartmentMapper;
import et.hrms.dal.mapping.education.EducationMapper;
import et.hrms.dal.mapping.employee.*;
import et.hrms.dal.model.education.Education;
import et.hrms.dal.model.employee.*;
import et.hrms.dal.repository.employee.EmployeeDetailRepository;
import et.hrms.dal.repository.employee.EmployeeRepository;
import et.hrms.dal.repository.structure.DepartmentRepository;
import et.hrms.dal.repository.structure.DepartmentUnderBranchRepository;
import et.hrms.dal.repository.structure.DepartmentUnderOrganizationRepository;
import et.hrms.exceptions.EmployeeNotFoundException;
import et.hrms.service.employee.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final EmployeeAddressMapper addressMapper;
    private final EmployeeDetailMapper employeeDetailMapper;
    private final DepartmentRepository departmentRepository;
    private final EmployeeDetailRepository employeeDetailRepository;
    private final DepartmentMapper departmentMapper;
    private final EducationMapper educationMapper;
    private final EmployeePromotionMapper promotionMapper;
    private final EmployeeAppearanceMapper appearanceMapper;
    private final FamilyMapper familyMapper;
    private final DepartmentUnderBranchRepository departmentUnderBranchRepository;
    private final DepartmentUnderOrganizationRepository departmentUnderOrganizationRepository;

    @Override
    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEmployee(employeeDTO);

        if (employeeDTO.getId() == null) {
            employee.setEmployeeDetails(createEmployeeDetails(employee, employeeDTO.getEmployeeDetails()));
            employee.setEmployeeAddresses(createEmployeeAddresses(employee, employeeDTO.getEmployeeAddresses()));
            employee.setEducations(createEmployeeEducations(employee, employeeDTO.getEducationDTOS()));
            employee.setEmployeePromotions(createEmployeePromotions(employee, employeeDTO.getEmployeePromotions()));
            employee.setEmployeeAppearance(createEmployeeAppearance(employee, employeeDTO.getEmployeeAppearanceDTO()));
            employee.setFamily(createFamily(employee, employeeDTO.getFamilyDTO()));
            validateAndSaveEmployee(employee);
        }

        return employeeMapper.toEmployeeDTO(employee);
    }

    private void validateAndSaveEmployee(Employee employee) {
        if (!employee.getEmployeeAddresses().isEmpty() && !employee.getEmployeeDetails().isEmpty()) {
            employeeRepository.save(employee);
        } else {
            throw new EntityNotFoundException("Employee has some problem persisting");
        }
    }

    private List<EmployeeDetail> createEmployeeDetails(Employee employee, List<EmployeeDetailDTO> employeeDetailDTOS) {
        return employeeDetailDTOS.stream()
                .map(dto -> createEmployeeDetail(employee, dto))
                .collect(Collectors.toList());
    }

    private EmployeeDetail createEmployeeDetail(Employee employee, EmployeeDetailDTO dto) {
        EmployeeDetail employeeDetail = employeeDetailMapper.toEmployeeDetail(dto);
        var department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department information is not found"));
        employeeDetail.setDepartment(department);
        employeeDetail.setEmployee(employee);
        return employeeDetail;
    }

    private List<EmployeeAddress> createEmployeeAddresses(Employee employee, List<EmployeeAddressDTO> employeeAddressDTOS) {
        return employeeAddressDTOS.stream()
                .map(dto -> createEmployeeAddress(employee, dto))
                .collect(Collectors.toList());
    }

    private EmployeeAddress createEmployeeAddress(Employee employee, EmployeeAddressDTO dto) {
        EmployeeAddress address = addressMapper.toEmployeeAddress(dto);
        address.setEmployee(employee);
        return address;
    }

    private List<Education> createEmployeeEducations(Employee employee, List<EducationDTO> employeeEducationDTOS) {
        return employeeEducationDTOS.stream()
                .map(dto -> createEmployeeEducation(employee, dto))
                .collect(Collectors.toList());
    }

    private Education createEmployeeEducation(Employee employee, EducationDTO dto) {
        Education education = educationMapper.toEducation(dto);
        education.setEmployee(employee);
        return education;
    }

    private List<EmployeePromotion> createEmployeePromotions(Employee employee, List<EmployeePromotionDTO> employeePromotionDTOS) {
        return employeePromotionDTOS.stream()
                .map(dto -> createEmployeePromotion(employee, dto))
                .collect(Collectors.toList());
    }

    private EmployeePromotion createEmployeePromotion(Employee employee, EmployeePromotionDTO dto) {
        EmployeePromotion promotion = promotionMapper.toEmployeePromotion(dto);
        promotion.setEmployee(employee);
        return promotion;
    }

    private EmployeeAppearance createEmployeeAppearance(Employee employee, EmployeeAppearanceDTO dto) {
        EmployeeAppearance appearance = appearanceMapper.toAppearance(dto);
        appearance.setEmployee(employee);
        return appearance;
    }

    private Family createFamily(Employee employee, FamilyDTO dto) {
        Family family = familyMapper.toFamily(dto);
        family.setEmployee(employee);
        return family;
    }

    @Override
    public List<EmployeeDTO> findEmployeeByDepartmentName(String departmentName, int page, int size) {
        var pageable = PageRequest.of(page, size);
        var employeeDetails = employeeDetailRepository.findEmployeeByDepartmentName(departmentName, pageable);
        return employeeDetails.stream()
                .map(EmployeeDetail::getEmployee)
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployeeList(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var employees = employeeRepository.findAll(pageable);
        return employees.stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        return convertToEmployeeDTO(employee);
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        var employeeDetailDTOS = employee.getEmployeeDetails().stream()
                .map(this::convertToEmployeeDetailDTO)
                .collect(Collectors.toList());

        var employeeAddressDTOS = employee.getEmployeeAddresses().stream()
                .map(addressMapper::toEmployeeAddressDTO)
                .collect(Collectors.toList());

        var employeeEducationDTOS = employee.getEducations().stream()
                .map(educationMapper::toEducationDTO)
                .collect(Collectors.toList());

        var employeePromotionDTOS = employee.getEmployeePromotions().stream()
                .map(promotionMapper::toEmployeePromotionDTO)
                .collect(Collectors.toList());

        var employeeDTO = employeeMapper.toEmployeeDTO(employee);
        employeeDTO.setEmployeeDetails(employeeDetailDTOS);
        employeeDTO.setEmployeeAddresses(employeeAddressDTOS);
        employeeDTO.setEducationDTOS(employeeEducationDTOS);
        employeeDTO.setEmployeePromotions(employeePromotionDTOS);
        employeeDTO.setEmployeeAppearanceDTO(appearanceMapper.toAppearanceDTO(employee.getEmployeeAppearance()));
        employeeDTO.setFamilyDTO(familyMapper.toFamilyDTO(employee.getFamily()));

        return employeeDTO;
    }

    private EmployeeDetailDTO convertToEmployeeDetailDTO(EmployeeDetail employeeDetail) {
        var departmentDTO = departmentMapper.toDepartment(employeeDetail.getDepartment());
        var employeeDetailDTO = employeeDetailMapper.toEmployeeDetailDTO(employeeDetail);
        employeeDetailDTO.setDepartmentId(employeeDetail.getDepartment().getId());
        return employeeDetailDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> searchEmployeesByName(String name) {
        List<Employee> employees = employeeRepository.searchByFirstNameOrLastName(name);
        return employees.stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}
