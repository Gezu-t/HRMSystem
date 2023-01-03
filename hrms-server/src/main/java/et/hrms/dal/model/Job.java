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
@Table(name = "job")
public class Job {

    @Id
    @SequenceGenerator(name = "job_id_gen",
            sequenceName = "job_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "job_id_gen")
    private Long id;
    private String jobTitle;

    private String jobResponsibility;

    @ManyToOne
    @JoinColumn(name = "job_level_id")
    private JobLevel jobLevel;

}
