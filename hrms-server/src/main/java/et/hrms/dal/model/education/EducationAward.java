package et.hrms.dal.model.education;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "education_award")
public class EducationAward {
  @Id
  @GeneratedValue(generator = "education_award_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "education_award_id_gen", sequenceName = "education_award_id_seq", allocationSize = 1)
  private Long id;
  private String awardName;
  private LocalDate awardDate;
  private String awardDescription;
  @OneToMany(mappedBy = "educationAward")
  private List<Education> educations;

}
