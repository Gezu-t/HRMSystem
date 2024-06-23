package com.hrmsystem.employeeservice.core.dal.model.branch;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "branch_address")
public class BranchAddress implements Serializable {

    @Serial
    private static final long serialVersionUID = -743290746686206556L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 15)
    private String telNumberHome;

    @Size(max = 15)
    private String telNumberOffice;

    @NotBlank
    @Size(max = 15)
    private String mobile;

    private Integer houseNumber;

    @Size(max = 50)
    private String street;

    private Integer streetNumber;

    @Size(max = 50)
    private String building;

    @Size(max = 10)
    private String floor;

    @Size(max = 10)
    private String flat;

    @Size(max = 50)
    private String region;

    @Size(max = 50)
    private String province;

    @Size(max = 50)
    private String city;

    @Size(max = 10)
    private String postalCode;

    @Size(max = 50)
    private String country;

    @OneToOne(mappedBy = "branchAddress")
    private Branch branch;

}