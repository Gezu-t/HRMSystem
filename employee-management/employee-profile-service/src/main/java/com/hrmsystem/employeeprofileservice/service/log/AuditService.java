package com.hrmsystem.employeeprofileservice.service.log;


import dal.model.AuditData;

import java.util.List;

public interface AuditService {


    void logAction(String username, String entity, String action, Long entityId);

    List<AuditData> logActionList(String username, String entity, String action, Long entityId);

    List<AuditData> getAllAudits();

    List<AuditData> getAuditsByUsername(String username);
}
