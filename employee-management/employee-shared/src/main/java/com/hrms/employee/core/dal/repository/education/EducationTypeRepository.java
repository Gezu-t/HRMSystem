package com.hrms.employee.core.dal.repository.education;

import com.hrms.employee.core.dal.model.education.EducationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationTypeRepository extends JpaRepository<EducationType, Long> {
}
