package et.hrms.dal.model.onboarding;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "material_type")
public class MaterialType {

  @Id
  @GeneratedValue(generator = "material_type_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "material_type_id_gen", sequenceName = "material_type_id_seq", allocationSize = 1)
  private Long id;
  @Column(name = "material_type_name", unique = true)
  private String name;
  private String description;

}
