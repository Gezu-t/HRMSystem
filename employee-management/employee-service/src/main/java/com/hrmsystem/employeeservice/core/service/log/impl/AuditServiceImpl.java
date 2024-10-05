package com.hrmsystem.employeeservice.core.service.log.impl;


import com.hrmsystem.employeeservice.core.service.log.AuditService;
import dal.model.AuditData;
import dal.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    public void logAction(String username, String entity, String action, Long entityId) {
        AuditData auditData = new AuditData();
        auditData.setUsername(username);
        auditData.setEntity(entity);
        auditData.setAction(action);
        auditData.setEntityId(entityId);
        auditData.setTimestamp(LocalDateTime.now());
        auditRepository.save(auditData);
    }




    @Override
    public List<AuditData> logActionList(String username, String entity, String action, Long entityId) {
        AuditData auditData = new AuditData();

        auditData.setUsername(username);
        auditData.setEntity(entity);
        auditData.setAction(action);
        auditData.setEntityId(entityId);
        auditData.setTimestamp(LocalDateTime.now());
        return Collections.singletonList(auditRepository.save(auditData));
    }

    @Override
    public List<AuditData> getAllAudits() {
        return auditRepository.findAll();
    }


    @Override
    public List<AuditData> getAuditsByUsername(String username) {
        return auditRepository.findByUsername(username);
    }


}
