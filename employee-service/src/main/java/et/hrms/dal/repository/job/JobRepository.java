package et.hrms.dal.repository.job;

import et.hrms.dal.model.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
