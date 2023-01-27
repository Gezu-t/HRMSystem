package et.hrms.dal.repository;

import et.hrms.dal.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("select o from Organization o where o.organizationName = :organizationName")
    Organization findByOrganizationName(@Param("organizationName") String organizationName);
}

