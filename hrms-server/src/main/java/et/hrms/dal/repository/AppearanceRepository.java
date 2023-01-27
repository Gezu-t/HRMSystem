package et.hrms.dal.repository;


import et.hrms.dal.model.Appearance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface AppearanceRepository extends JpaRepository<Appearance, Long> {
    @Query("select a from Appearance a where a.employee.id = :id")
    Optional<Appearance> findByEmployeeId(@Param("id") Long id);

    @Query("select a from Appearance a")
    @Async
    CompletableFuture<Page<Appearance>> findAppearanceDetailAsync(Pageable pageable);


}
