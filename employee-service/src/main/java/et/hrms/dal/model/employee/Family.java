package et.hrms.dal.model.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "family")
public class Family implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nationality;

    @Column(length = 10)
    private String payGrade;

    @Column(name = "date_of_birth")
    private LocalDate dateBirth;

    @Column(length = 10)
    private String gender;

    @Column(length = 50, nullable = false)
    private String familyFirstName;

    @Column(length = 50, nullable = false)
    private String familyLastName;

    @Column(length = 15)
    private String emergencyContact;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}
