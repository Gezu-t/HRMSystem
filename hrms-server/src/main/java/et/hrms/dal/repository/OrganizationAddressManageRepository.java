package et.hrms.dal.repository;

import et.hrms.dal.model.OrganizationAddressManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationAddressManageRepository extends JpaRepository<OrganizationAddressManagement, Long> {
}
