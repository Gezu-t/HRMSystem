package com.gtltek.employee.core.service.employee.impl;

import com.gtltek.employee.core.service.employee.EmployeeOnboardingService;
import com.gtltek.employee.core.dal.repository.onboarding.MaterialTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeOnboardingServiceImpl implements EmployeeOnboardingService {


  private final MaterialTypeRepository materialTypeRepository;

}
