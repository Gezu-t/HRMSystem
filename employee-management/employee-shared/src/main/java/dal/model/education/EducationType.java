package dal.model.education;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "education_type")
public class EducationType implements Serializable {

  @Serial
  private static final long serialVersionUID = 1851314719597026566L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String educationTypeId;
  @Column(name = "education_type_name")
  private String name;

  @OneToMany(mappedBy = "educationType")
  private List<EducationDetail> educationDetails;

}
