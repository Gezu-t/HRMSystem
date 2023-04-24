package et.hrms.dal.model.job;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_level")
public class JobLevel {


    @Id
    @SequenceGenerator(name = "job_level_id_sequence",
            sequenceName = "job_level_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "job_level_id_sequence")
    private Long id;

    private String levelName; //team leader

    private String levelPeriod;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Job> jobList;

}
