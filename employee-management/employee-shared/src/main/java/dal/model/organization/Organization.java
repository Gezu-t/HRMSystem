package dal.model.organization;

import dal.model.branch.Address;
import dal.model.branch.Branch;
import dal.model.department.Department;
import dal.model.employee.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "organization")
public class Organization implements Serializable {

    @Serial
    private static final long serialVersionUID = -5895464065664543655L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String organizationName;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String organizationCode;

    @Column(nullable = false)
    private LocalDate establishmentDate;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Branch> branches;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Department> departments;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Owners> owners;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

}
