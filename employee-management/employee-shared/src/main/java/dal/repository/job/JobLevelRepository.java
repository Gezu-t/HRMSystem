package dal.repository.job;

import dal.model.job.JobLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobLevelRepository extends JpaRepository<JobLevel, Long> {
}
