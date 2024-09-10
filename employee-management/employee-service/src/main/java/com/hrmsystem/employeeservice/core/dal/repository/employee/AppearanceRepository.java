package com.hrmsystem.employeeservice.core.dal.repository.employee;


import dal.model.employee.EmployeeAppearance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AppearanceRepository extends JpaRepository<EmployeeAppearance, Long> {
    @Query("select a from EmployeeAppearance a where a.employee.id = :id")
    Optional<EmployeeAppearance> findByEmployeeId(@Param("id") Long id);

    @Query("select a from EmployeeAppearance a")
    @Async
    CompletableFuture<Page<EmployeeAppearance>> findAppearanceDetailAsync(Pageable pageable);


}
