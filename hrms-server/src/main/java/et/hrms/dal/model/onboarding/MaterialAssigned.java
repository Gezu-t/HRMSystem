package et.hrms.dal.model.onboarding;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "material_assigned")
public class MaterialAssigned {

  @Id
  @GeneratedValue(generator = "material_assigned_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "material_assigned_id_gen", sequenceName = "material_assigned_id_seq", allocationSize = 1)
  private Long id;
  private LocalDate assignedDate;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "materialTypeId")
  private MaterialType materialType;
  private String materialSpecification;
  private String serialCode;
  private String model;
  private LocalDate manufacturingDate;
  private String statusOfMaterial;

  private LocalDate overDueDate;
  @Enumerated(EnumType.STRING)
  private MaterialAssignedStatus materialAssignedStatus;
  private LocalDate returnDate;
}
