package et.hrms.dal.repository.education;

import et.hrms.dal.model.education.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EducationRepository extends JpaRepository<Education, Long> {


    @Query("select e from Education e where e.institution = :name")
    Education findByInstitution(@Param("name") String name);
}
