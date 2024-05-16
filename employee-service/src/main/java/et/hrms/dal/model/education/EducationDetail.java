package et.hrms.dal.model.education;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "education_detail")
public class EducationDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String degree;
  private String major;
  private String minor;
  private String educationGrade;
  private String educationGradePoint;
  private String gpa;
  private String gpaOutOf;
  private String gpaOutOfScale;
  private Year academicYear;
  @Enumerated(EnumType.STRING)
  private EducationStatus educationStatus;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "educationId")
  private Education education;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "educationTypeId")
  private EducationType educationType;
}
