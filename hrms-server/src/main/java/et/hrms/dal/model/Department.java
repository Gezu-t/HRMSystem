package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
    private String locations;
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<EmployeeDepartmentManagement> employeeDepartmentManagements;


}
