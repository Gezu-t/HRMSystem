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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String educationLevelName;
  @OneToMany(mappedBy = "educationLevel")
  private List<Education> educations;

}
