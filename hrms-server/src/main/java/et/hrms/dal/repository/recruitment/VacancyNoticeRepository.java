package et.hrms.dal.repository.recruitment;

import et.hrms.dal.model.recruitment.VacancyNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyNoticeRepository extends JpaRepository<VacancyNotice, Long> {
}
