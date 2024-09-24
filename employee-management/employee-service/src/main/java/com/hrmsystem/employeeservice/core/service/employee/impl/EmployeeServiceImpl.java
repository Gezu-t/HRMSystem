package com.hrmsystem.employeeservice.core.service.employee.impl;

import com.hrmsystem.employeeservice.core.dal.dto.education.EducationDTO;
import com.hrmsystem.employeeservice.core.dal.dto.employee.*;
import com.hrmsystem.employeeservice.core.dal.mapping.department.DepartmentMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.education.EducationMapper;
import com.hrmsystem.employeeservice.core.dal.mapping.employee.*;
import com.hrmsystem.employeeservice.core.service.employee.EmployeeService;
import dal.model.branch.Branch;
import dal.model.department.Department;
import dal.model.education.Education;
import dal.model.employee.*;
import dal.model.organization.Organization;
import dal.repository.branch.BranchRepository;
import dal.repository.department.DepartmentRepository;
import dal.repository.employee.EmployeeDetailRepository;
import dal.repository.employee.EmployeeRepository;
import dal.repository.organization.OrganizationRepository;
import exceptions.EmployeeNotFoundException;
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
    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;
    private final DepartmentMapper departmentMapper;
    private final EducationMapper educationMapper;
    private final EmployeePromotionMapper promotionMapper;
    private final EmployeeAppearanceMapper appearanceMapper;
    private final FamilyMapper familyMapper;

    @Override
    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // Convert DTO to entity
        Employee employee = employeeMapper.toEmployee(employeeDTO);

        // Validate employeeprofile data
        validateEmployeeData(employeeDTO);

        // Fetch related entities if their IDs are provided
        setRelatedEntities(employeeDTO, employee);

        // Create associated entities and set bidirectional links
        employee.setEmployeeDetails(createEmployeeDetails(employee, employeeDTO.getEmployeeDetails()));
        employee.setEmployeeAddresses(createEmployeeAddresses(employee, employeeDTO.getEmployeeAddresses()));
        employee.setEducations(createEmployeeEducations(employee, employeeDTO.getEducations()));
        employee.setEmployeePromotions(createEmployeePromotions(employee, employeeDTO.getEmployeePromotions()));
        employee.setEmployeeAppearance(createEmployeeAppearance(employee, employeeDTO.getEmployeeAppearance()));
        employee.setFamily(createFamily(employee, employeeDTO.getFamily()));

        // Save the employeeprofile and related entities using cascading
        employeeRepository.save(employee);

        return employeeMapper.toEmployeeDTO(employee);
    }

    private void validateEmployeeData(EmployeeDTO employeeDTO) {
        if (employeeDTO.getEmployeeAddresses() == null || employeeDTO.getEmployeeAddresses().isEmpty()) {
            throw new IllegalArgumentException("Employee addresses must be provided.");
        }
        if (employeeDTO.getEmployeeDetails() == null || employeeDTO.getEmployeeDetails().isEmpty()) {
            throw new IllegalArgumentException("Employee details must be provided.");
        }
    }

    private void setRelatedEntities(EmployeeDTO employeeDTO, Employee employee) {
        // Set Department
        if (employeeDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found for ID: " + employeeDTO.getDepartmentId()));
            employee.setDepartment(department);
        }

        // Set Branch
        if (employeeDTO.getBranchId() != null) {
            Branch branch = branchRepository.findById(employeeDTO.getBranchId())
                    .orElseThrow(() -> new EntityNotFoundException("Branch not found for ID: " + employeeDTO.getBranchId()));
            employee.setBranch(branch);
        }

        // Set Organization
        if (employeeDTO.getOrganizationId() != null) {
            Organization organization = organizationRepository.findById(employeeDTO.getOrganizationId())
                    .orElseThrow(() -> new EntityNotFoundException("Organization not found for ID: " + employeeDTO.getOrganizationId()));
            employee.setOrganization(organization);
        }
    }

    private List<EmployeeDetail> createEmployeeDetails(Employee employee, List<EmployeeDetailDTO> employeeDetailDTOS) {
        return employeeDetailDTOS.stream()
                .map(dto -> createEmployeeDetail(employee, dto))
                .collect(Collectors.toList());
    }

    private EmployeeDetail createEmployeeDetail(Employee employee, EmployeeDetailDTO dto) {
        EmployeeDetail employeeDetail = employeeDetailMapper.toEmployeeDetail(dto);
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found for ID: " + dto.getDepartmentId()));
        employeeDetail.setDepartment(department);
        employeeDetail.setEmployee(employee); // Set bidirectional link
        return employeeDetail;
    }

    private List<EmployeeAddress> createEmployeeAddresses(Employee employee, List<EmployeeAddressDTO> employeeAddressDTOS) {
        return employeeAddressDTOS.stream()
                .map(dto -> createEmployeeAddress(employee, dto))
                .collect(Collectors.toList());
    }

    private EmployeeAddress createEmployeeAddress(Employee employee, EmployeeAddressDTO dto) {
        EmployeeAddress address = addressMapper.toEmployeeAddress(dto);
        address.setEmployee(employee); // Set bidirectional link
        return address;
    }

    private List<Education> createEmployeeEducations(Employee employee, List<EducationDTO> employeeEducationDTOS) {
        return employeeEducationDTOS.stream()
                .map(dto -> createEmployeeEducation(employee, dto))
                .collect(Collectors.toList());
    }

    private Education createEmployeeEducation(Employee employee, EducationDTO dto) {
        Education education = educationMapper.toEducation(dto);
        education.setEmployee(employee); // Set bidirectional link
        return education;
    }

    private List<EmployeePromotion> createEmployeePromotions(Employee employee, List<EmployeePromotionDTO> employeePromotionDTOS) {
        return employeePromotionDTOS.stream()
                .map(dto -> createEmployeePromotion(employee, dto))
                .collect(Collectors.toList());
    }

    private EmployeePromotion createEmployeePromotion(Employee employee, EmployeePromotionDTO dto) {
        EmployeePromotion promotion = promotionMapper.toEmployeePromotion(dto);
        promotion.setEmployee(employee); // Set bidirectional link
        return promotion;
    }

    private EmployeeAppearance createEmployeeAppearance(Employee employee, EmployeeAppearanceDTO dto) {
        EmployeeAppearance appearance = appearanceMapper.toAppearance(dto);
        appearance.setEmployee(employee); // Set bidirectional link
        return appearance;
    }

    private Family createFamily(Employee employee, FamilyDTO dto) {
        Family family = familyMapper.toFamily(dto);
        family.setEmployee(employee); // Set bidirectional link
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
        employeeDTO.setEducations(employeeEducationDTOS);
        employeeDTO.setEmployeePromotions(employeePromotionDTOS);
        employeeDTO.setEmployeeAppearance(appearanceMapper.toAppearanceDTO(employee.getEmployeeAppearance()));
        employeeDTO.setFamily(familyMapper.toFamilyDTO(employee.getFamily()));

        return employeeDTO;
    }

    private EmployeeDetailDTO convertToEmployeeDetailDTO(EmployeeDetail employeeDetail) {
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
