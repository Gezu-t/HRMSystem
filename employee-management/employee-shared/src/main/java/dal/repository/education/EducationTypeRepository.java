package dal.repository.education;

import dal.model.education.EducationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationTypeRepository extends JpaRepository<EducationType, Long> {
}
