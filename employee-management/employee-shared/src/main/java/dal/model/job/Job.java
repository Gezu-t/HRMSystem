package dal.model.job;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobCode;
    private String title;
    private Integer maxSalary;
    private Integer minSalary;
    private String description;

    @ManyToOne
    @JoinColumn(name = "job_level_id")
    private JobLevel jobLevel;
}
