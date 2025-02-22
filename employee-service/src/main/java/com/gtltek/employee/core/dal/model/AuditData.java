package com.gtltek.employee.core.dal.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@Entity
@Table(name = "audit", schema = "public")
public class AuditData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String entity;
    private String action;
    private Long entityId;
    private LocalDateTime timestamp;
}
