package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @SequenceGenerator(name = "department_id_gen",
            sequenceName = "department_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "department_id_gen")
    private Long id;
    private String departmentNumber;
    private String departmentName;
    private String locations;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;


    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;



}
