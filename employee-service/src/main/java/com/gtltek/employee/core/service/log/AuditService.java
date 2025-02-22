package com.gtltek.employee.core.service.log;


import com.gtltek.employee.core.dal.model.AuditData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AuditService {


    void logAction(String username, String entity, String action, Long entityId);

    List<AuditData> logActionList(String username, String entity, String action, Long entityId);

    List<AuditData> getAllAudits();

    @Transactional(readOnly = true)
    Page<AuditData> getAllAudits(Pageable pageable);

    List<AuditData> getAuditsByUsername(String username);

    @Transactional(readOnly = true)
    Page<AuditData> getAuditsByUsername(String username, Pageable pageable);

    @Transactional(readOnly = true)
    Optional<AuditData> getAuditById(Long id);

    @Transactional(readOnly = true)
    List<AuditData> getAuditsByEntityAndAction(String entity, String action);

    @Transactional(readOnly = true)
    Page<AuditData> getAuditsByEntityAndAction(String entity, String action, Pageable pageable);
}
