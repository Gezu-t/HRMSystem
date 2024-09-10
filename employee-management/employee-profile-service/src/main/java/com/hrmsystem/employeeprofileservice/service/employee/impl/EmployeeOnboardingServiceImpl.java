package com.hrmsystem.employeeprofileservice.service.employee.impl;

import com.hrmsystem.employeeprofileservice.service.employee.EmployeeOnboardingService;
import dal.repository.onboarding.MaterialTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeOnboardingServiceImpl implements EmployeeOnboardingService {


  private final MaterialTypeRepository materialTypeRepository;

}
