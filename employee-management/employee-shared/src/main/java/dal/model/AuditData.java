package dal.model;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String entity;
    private String action;
    private Long entityId;
    private LocalDateTime timestamp;
}
