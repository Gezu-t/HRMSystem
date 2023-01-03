package et.hrms.dal.model;

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
@Table(name = "family")
public class Family {

    @Id
    @SequenceGenerator(name = "family_id_gen",
            sequenceName = "family_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "family_id_gen")
    private Long id;

    private String nationality;
    private String payGrade;
    private String date_Birth;
    private String gender;
    private String familyFirstName;
    private String familyLastName;
    private String emergencyContact;

    @OneToOne
    @JoinColumn(name = "employee")
    private Employee employee;
}
