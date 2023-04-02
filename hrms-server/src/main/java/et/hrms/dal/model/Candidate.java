package et.hrms.dal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidate", schema = "public")
public class Candidate  {

    @Id
    @SequenceGenerator(name = "candidate_id_gen",
            sequenceName = "candidate_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "candidate_id_gen")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate registrationDate;
    private LocalDate dateOfBirth;
    private String experience;
    private String interview;
    private String levelOfEducation;
    private String middleName;
    private String mobile;
    private String officeTelephone;
    private String practical;
    private String recommendation;
    private String telResidence;
    private String batchCode;
    private GenderStatus gender;
    @Enumerated(EnumType.STRING)
    private CandidateType candidateType;
    private String other;
    private String noPage;
    private String writtenExam;
    private String cgpa;
    private Long candidateId;
    private String status;

    @OneToMany(mappedBy = "candidate")
    private List<Recruitment> recruitments;

}
