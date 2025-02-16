package com.hrms.employee.core.service.employee.impl;

import com.hrms.employee.core.service.employee.EmployeeOnboardingService;
import com.hrms.employee.core.dal.repository.onboarding.MaterialTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeOnboardingServiceImpl implements EmployeeOnboardingService {


  private final MaterialTypeRepository materialTypeRepository;

}
