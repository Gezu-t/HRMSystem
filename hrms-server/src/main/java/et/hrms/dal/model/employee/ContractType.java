package et.hrms.dal.model.employee;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "contract_type")
public class ContractType {
  @Id
  @GeneratedValue(generator = "contract_type_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "contract_type_id_gen", sequenceName = "contract_type_id_seq", allocationSize = 1)
  private Long id;
  @JoinColumn(name = "contract_type_name", unique = true, nullable = false)
  private String contractTypeName;
  private String description;
}
