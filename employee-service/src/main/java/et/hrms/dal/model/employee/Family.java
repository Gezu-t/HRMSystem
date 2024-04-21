package et.hrms.dal.model.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nationality;
    private String payGrade;
    private LocalDate dateBirth;
    private String gender;
    private String familyFirstName;
    private String familyLastName;
    private String emergencyContact;

    @OneToOne
    @JoinColumn(name = "employee")
    private Employee employee;
}
