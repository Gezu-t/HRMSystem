package et.hrms.dal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "mobile", nullable = false, length = 15)
    private String mobile;

    @Column(length = 100)
    private String address;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = true)
    private LocalDate dateOfBirth;

    @Column(length = 200)
    private String experience;

    @Column(length = 50)
    private String levelOfEducation;

    @Column(length = 50)
    private String officeTelephone;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private GenderType gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateTypeId")
    private CandidateType candidateType;

    @Column(length = 1000)
    private String other;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recruitment> recruitments;
}
