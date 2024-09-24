package com.hrmsystem.employeeprofileservice.service.employeeprofile.impl;

import com.hrmsystem.employeeprofileservice.service.employeeprofile.EmployeeOnboardingService;
import dal.repository.onboarding.MaterialTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeOnboardingServiceImpl implements EmployeeOnboardingService {


  private final MaterialTypeRepository materialTypeRepository;

}
