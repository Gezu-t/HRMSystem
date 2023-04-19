package et.hrms.dal.model.education;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "education_level")
public class EducationLevel {
  @Id
  @GeneratedValue(generator = "education_level_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "education_level_id_gen", sequenceName = "education_level_id_seq", allocationSize = 1)
  private Long id;
  private String educationLevelName;
  @OneToMany(mappedBy = "educationLevel")
  private List<Education> educations;

}
