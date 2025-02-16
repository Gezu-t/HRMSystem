package com.hrms.employee.core.dal.repository;


import com.hrms.employee.core.dal.model.AuditData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuditRepository extends JpaRepository<AuditData, Long> {

    @Query("select a from AuditData a where a.username = :username")
    List<AuditData> findByUsername(@Param("username") String username);

    // Find audit logs by username (without pagination)
//    List<AuditData> findByUsername(String username);

    // Find audit logs by username with pagination
    Page<AuditData> findByUsername(String username, Pageable pageable);

    // Find audit logs by entity and action (without pagination)
    List<AuditData> findByEntityAndAction(String entity, String action);

    // Find audit logs by entity and action with pagination
    Page<AuditData> findByEntityAndAction(String entity, String action, Pageable pageable);

}
