package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Table(name = "audit", schema = "public")
public class AuditData {


    @Id
    @SequenceGenerator(name = "gen_audit_id", sequenceName = "seq_gen_audit_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_gen_audit_id")
    private Long id;
    private String username;
    private String entity;
    private String action;
    private Long entityId;
    private LocalDateTime timestamp;
}
