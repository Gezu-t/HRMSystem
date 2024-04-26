package et.hrms.service.employee.impl;

import et.hrms.dal.repository.onboarding.MaterialAssignedRepository;
import et.hrms.dal.repository.onboarding.MaterialTypeRepository;
import et.hrms.service.employee.EmployeeOnboardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeOnboardingServiceImpl implements EmployeeOnboardingService {


  private final MaterialTypeRepository materialTypeRepository;
  private final MaterialAssignedRepository materialAssignedRepository;

}
