package et.hrms.dal.repository;

import et.hrms.dal.model.VacancyNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyNoticeRepository extends JpaRepository<VacancyNotice, Long> {
}
