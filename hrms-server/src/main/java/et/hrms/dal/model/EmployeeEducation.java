package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "employee_education", schema = "public")
public class EmployeeEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_education_gen")
    @SequenceGenerator(name = "employee_education_gen", sequenceName = "employee_education_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "education_id")
    private Education education;

}
