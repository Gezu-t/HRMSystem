package et.hrms.dal.model.recruitment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vacancy_notice", schema = "public")
public class VacancyNotice {

  @Id
  @SequenceGenerator(name = "vacancy_notice_id_gen",
          sequenceName = "vacancy_notice_id_seq",
          allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "vacancy_notice_id_gen")
  private Long id;
  private String jobTitle;
  private String jobDescription;
  private Integer numberOfPositions;
  private LocalDate publishDate;
  private LocalDate closeDate;

  @OneToOne
  private Advertisement advertisement;

  // Getters and setters
  @OneToMany(mappedBy = "vacancyNotice")
  private Collection<Recruitment> recruitment;

  public Collection<Recruitment> getRecruitment() {
    return recruitment;
  }

  public void setRecruitment(Collection<Recruitment> recruitment) {
    this.recruitment = recruitment;
  }
}
