package et.hrms.dal.model.education;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "education_type")
public class EducationType {

  @Id
  @GeneratedValue(generator = "education_type_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "education_type_id_gen", sequenceName = "education_type_id_seq", allocationSize = 1)
  private Long id;
  private String educationTypeId;
  @Column(name = "education_type_name")
  private String name;

  @OneToMany(mappedBy = "educationType")
  private List<EducationDetail> educationDetails;

}
