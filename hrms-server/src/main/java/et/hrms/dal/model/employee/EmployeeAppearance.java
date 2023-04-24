package et.hrms.dal.model.employee;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_appearance")
public class EmployeeAppearance {

    @Id
    @SequenceGenerator(name = "appearance_id_seq",
            sequenceName = "appearance_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "appearance_id_seq")
    private Long id;
    private String height;
    private String weight;
    private String hairColor;
    private String eyeColor;
    private String skinColor;
    private String bloodGroup;
    private String chest;

    @OneToOne
    @JoinColumn(name = "employee")
    private Employee employee;

}
