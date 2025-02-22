package com.gtltek.messaging.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = -1726598984567894321L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<AuthUser> users;

    public Role(String name) {
        this.name = name;
    }
}
