package et.hrms.dal.repository;

import et.hrms.dal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
