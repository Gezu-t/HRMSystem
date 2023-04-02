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
public class Recruitment {

    @Id
    @SequenceGenerator(name = "recruitment_id_gen",
            sequenceName = "recruitment_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "recruitment_id_gen")
    private Long id;

    @ManyToOne
    private Initiator initiator;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private VacancyNotice vacancyNotice;

    private LocalDate applicationDate;
    private LocalDate interviewDate;
    private Boolean hired;
    private LocalDate hireDate;

    // Getters and setters
}
