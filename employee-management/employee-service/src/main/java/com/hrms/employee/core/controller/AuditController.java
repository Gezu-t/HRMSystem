package com.hrms.employee.core.controller;


import com.hrms.employee.core.dal.model.AuditData;
import com.hrms.employee.core.service.log.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audits")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping("/entity-action")
    public ResponseEntity<List<AuditData>> getAuditsByEntityAndAction(
            @RequestParam String entity,
            @RequestParam String action) {
        return ResponseEntity.ok(auditService.getAuditsByEntityAndAction(entity, action));
    }

    @GetMapping("/entity-action/paginated")
    public ResponseEntity<Page<AuditData>> getAuditsByEntityAndActionPaginated(
            @RequestParam String entity,
            @RequestParam String action,
            Pageable pageable) {
        return ResponseEntity.ok(auditService.getAuditsByEntityAndAction(entity, action, pageable));
    }
}