package et.hrms.dal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recruitment")
public class Recruitment {

    @Id
    @SequenceGenerator(name = "recruitment_id_gen",
            sequenceName = "recruitment_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "recruitment_id_gen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiatorId")
    private Initiator initiator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateId")
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancyNoticeId")
    private VacancyNotice vacancyNotice;

    private LocalDate applicationDate;
    private LocalDate interviewDate;
    private Boolean hired;
    private LocalDate hireDate;

}
