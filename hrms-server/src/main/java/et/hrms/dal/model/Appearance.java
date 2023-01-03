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
@Table(name = "appearance")
public class Appearance {

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
