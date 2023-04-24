package et.hrms.dal.model.education;


import et.hrms.dal.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor@Setter@Getter@NoArgsConstructor
@Entity
@Table(name = "education")
public class Education {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "education_id_gen")
  @SequenceGenerator(name = "education_id_gen",sequenceName = "education_id_sequence", allocationSize = 1)
  private Long id;
  private LocalDate completionDate;
  private LocalDate startDate;
  private LocalDate endDate;
  private String institution;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "educationLevelId")
  private EducationLevel educationLevel;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "educationAwardId")
  private EducationAward educationAward;
  // auditing
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  @OneToMany(mappedBy = "education")
  private List<EducationDetail> educationDetails;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employeeId")
  private Employee employee;



}
