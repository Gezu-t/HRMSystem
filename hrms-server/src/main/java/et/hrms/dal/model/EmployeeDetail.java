package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "employee_detail", schema = "public")
@NoArgsConstructor
public class EmployeeDetail {

    @Id
    @GeneratedValue(generator = "employee_detail_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "employee_detail_gen", sequenceName = "employee_detail_gen_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;



}
