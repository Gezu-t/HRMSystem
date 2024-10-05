package com.hrmsystem.employeeprofileservice.service.employeeprofile.impl;


import com.hrmsystem.employeeprofileservice.controller.client.EmployeeServiceClient;
import com.hrmsystem.employeeprofileservice.dal.dto.EmployeeProfileDTO;
import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.EmployeeProfileMapper;
import com.hrmsystem.employeeprofileservice.dal.model.EmployeeProfile;
import com.hrmsystem.employeeprofileservice.dal.repository.EmployeeProfileRepository;
import com.hrmsystem.employeeprofileservice.service.employeeprofile.EmployeeProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeProfileMapper employeeProfileMapper;
    private final EmployeeServiceClient employeeServiceClient;

    @Override
    public EmployeeProfileDTO createProfile(EmployeeProfileDTO employeeProfileDTO) {

        EmployeeDTO employeeDTO = employeeServiceClient.getEmployeeById(employeeProfileDTO.getEmployeeId());
        if (employeeDTO == null) {
            throw new IllegalArgumentException("Employee not found with ID: " + employeeProfileDTO.getEmployeeId());
        }

        EmployeeProfile employeeProfile = employeeProfileMapper.toEmployeeProfile(employeeProfileDTO);
        EmployeeProfile savedProfile = employeeProfileRepository.save(employeeProfile);
        return employeeProfileMapper.toEmployeeProfileDTO(savedProfile);
    }


}
