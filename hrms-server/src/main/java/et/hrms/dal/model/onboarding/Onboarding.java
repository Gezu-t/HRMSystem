package et.hrms.dal.model.onboarding;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
@Entity@Table(name = "employee_onboarding")
public class Onboarding {

  @Id
  @GeneratedValue(generator = "onboarding_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "onboarding_id_gen", sequenceName = "onboarding_id_seq", allocationSize = 1)
  private Long id;
  private LocalDate firstDate;

}
