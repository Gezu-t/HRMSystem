package com.gtltek.employee.core.dal.model.organization;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Owners implements Serializable {


    @Serial
    private static final long serialVersionUID = 4823557520764881845L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String birthDate;
    private String birthPlace;
    private String nationality;
    private String idNumber;
    private String role;
    private String email;
    private String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;


}
