package et.hrms.dal.repository.recruitment;

import et.hrms.dal.model.recruitment.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
