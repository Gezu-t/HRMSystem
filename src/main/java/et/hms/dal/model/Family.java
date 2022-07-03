package et.hms.dal.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "family", schema = "public")
public class Family {

    @Id
    @SequenceGenerator(name = "family_id_seq", sequenceName = "family_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "family_id_seq")
    private Long id;
    private String religion;
    private String nationality;
    private String nation;
    private String payGrade;
    private String date_Birth;
    private String emergency_Contact;
    private String gender;
    private String relation_Type;
    private String familyFirstName;
    private String familyMiddleName;
    private String familyLastName;
    private String emergencyContact;
}
