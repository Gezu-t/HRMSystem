package com.hrms.employee.core.service.log.impl;

import com.hrms.employee.core.service.log.AuditService;
import com.hrms.employee.core.dal.model.AuditData;
import com.hrms.employee.core.dal.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    @Transactional
    public void logAction(String username, String entity, String action, Long entityId) {
        try {
            AuditData auditData = AuditData.builder()
                    .username(username)
                    .entity(entity)
                    .action(action)
                    .entityId(entityId)
                    .timestamp(LocalDateTime.now())
                    .build();
            auditRepository.save(auditData);
            log.info("Audit log saved successfully for user: {}, entity: {}, action: {}", username, entity, action);
        } catch (Exception e) {
            log.error("Failed to save audit log for user: {}, entity: {}, action: {}", username, entity, action, e);
            throw new RuntimeException("Failed to save audit log", e);
        }
    }

    @Override
    @Transactional
    public List<AuditData> logActionList(String username, String entity, String action, Long entityId) {
        try {
            AuditData auditData = AuditData.builder()
                    .username(username)
                    .entity(entity)
                    .action(action)
                    .entityId(entityId)
                    .timestamp(LocalDateTime.now())
                    .build();
            AuditData savedAuditData = auditRepository.save(auditData);
            log.info("Audit log saved successfully for user: {}, entity: {}, action: {}", username, entity, action);
            return Collections.singletonList(savedAuditData);
        } catch (Exception e) {
            log.error("Failed to save audit log for user: {}, entity: {}, action: {}", username, entity, action, e);
            throw new RuntimeException("Failed to save audit log", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditData> getAllAudits() {
        try {
            return auditRepository.findAll();
        } catch (Exception e) {
            log.error("Failed to fetch all audit logs", e);
            throw new RuntimeException("Failed to fetch audit logs", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuditData> getAllAudits(Pageable pageable) {
        try {
            return auditRepository.findAll(pageable);
        } catch (Exception e) {
            log.error("Failed to fetch paginated audit logs", e);
            throw new RuntimeException("Failed to fetch paginated audit logs", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditData> getAuditsByUsername(String username) {
        try {
            return auditRepository.findByUsername(username);
        } catch (Exception e) {
            log.error("Failed to fetch audit logs for username: {}", username, e);
            throw new RuntimeException("Failed to fetch audit logs by username", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuditData> getAuditsByUsername(String username, Pageable pageable) {
        try {
            return auditRepository.findByUsername(username, pageable);
        } catch (Exception e) {
            log.error("Failed to fetch paginated audit logs for username: {}", username, e);
            throw new RuntimeException("Failed to fetch paginated audit logs by username", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuditData> getAuditById(Long id) {
        try {
            return auditRepository.findById(id);
        } catch (Exception e) {
            log.error("Failed to fetch audit log by id: {}", id, e);
            throw new RuntimeException("Failed to fetch audit log by id", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditData> getAuditsByEntityAndAction(String entity, String action) {
        try {
            return auditRepository.findByEntityAndAction(entity, action);
        } catch (Exception e) {
            log.error("Failed to fetch audit logs for entity: {} and action: {}", entity, action, e);
            throw new RuntimeException("Failed to fetch audit logs by entity and action", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuditData> getAuditsByEntityAndAction(String entity, String action, Pageable pageable) {
        try {
            return auditRepository.findByEntityAndAction(entity, action, pageable);
        } catch (Exception e) {
            log.error("Failed to fetch paginated audit logs for entity: {} and action: {}", entity, action, e);
            throw new RuntimeException("Failed to fetch paginated audit logs by entity and action", e);
        }
    }
}