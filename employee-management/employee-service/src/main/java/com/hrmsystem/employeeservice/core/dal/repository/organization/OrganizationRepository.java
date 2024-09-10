package com.hrmsystem.employeeservice.core.dal.repository.organization;

import dal.model.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("select o from Organization o where o.organizationName = :organizationName")
    Organization findByOrganizationName(@Param("organizationName") String organizationName);
}

