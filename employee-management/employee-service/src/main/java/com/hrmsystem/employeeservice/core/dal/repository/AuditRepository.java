package com.hrmsystem.employeeservice.core.dal.repository;


import com.hrmsystem.employeeservice.core.dal.model.AuditData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuditRepository extends JpaRepository<AuditData, Long> {

    @Query("select a from AuditData a where a.username = :username")
    List<AuditData> findByUsername(@Param("username") String username);
}
