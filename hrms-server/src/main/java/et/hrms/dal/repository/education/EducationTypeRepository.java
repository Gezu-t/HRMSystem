package et.hrms.dal.repository.education;

import et.hrms.dal.model.education.EducationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationTypeRepository extends JpaRepository<EducationType, Long> {
}
