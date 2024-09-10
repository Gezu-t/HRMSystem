package com.hrmsystem.employeeprofileservice.service;

import com.hrmsystem.employeeprofileservice.controller.client.EmployeeClient;
import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeDTO;
import com.hrmsystem.employeeprofileservice.dal.model.EmployeeProfile;
import com.hrmsystem.employeeprofileservice.dal.repository.EmployeeProfileRepository;
import exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeProfileService {
    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeClient employeeClient;

    public EmployeeProfile createEmployeeProfile(EmployeeProfile employeeProfile) {
        EmployeeDTO employee = employeeClient.getEmployeeById(employeeProfile.getEmployeeId());
        if (employee == null) {
            throw new EntityNotFoundException("Employee not found with id: " + employeeProfile.getEmployeeId());
        }
        return employeeProfileRepository.save(employeeProfile);
    }

    public EmployeeProfile getEmployeeProfile(Long employeeId) {
        return employeeProfileRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee Profile not found for employee id: " + employeeId));
    }

    public EmployeeProfile updateEmployeeProfile(EmployeeProfile employeeProfile) {
        EmployeeProfile existingProfile = getEmployeeProfile(employeeProfile.getEmployeeId());
        // Update fields
        existingProfile.setAddress(employeeProfile.getAddress());
        existingProfile.setPhoneNumber(employeeProfile.getPhoneNumber());
        existingProfile.setEmergencyContact(employeeProfile.getEmergencyContact());
        existingProfile.setDepartment(employeeProfile.getDepartment());
        existingProfile.setPosition(employeeProfile.getPosition());
        existingProfile.setManagerId(employeeProfile.getManagerId());
        existingProfile.setEducationLevel(employeeProfile.getEducationLevel());
        existingProfile.setSkills(employeeProfile.getSkills());
        return employeeProfileRepository.save(existingProfile);
    }

    public void deleteEmployeeProfile(Long employeeId) {
        EmployeeProfile profile = getEmployeeProfile(employeeId);
        employeeProfileRepository.delete(profile);
    }

    public List<EmployeeProfile> getEmployeeProfilesByDepartment(String department) {
        return employeeProfileRepository.findByDepartment(department);
    }

    public List<EmployeeProfile> getEmployeeProfilesByManager(Long managerId) {
        return employeeProfileRepository.findByManagerId(managerId);
    }
}
