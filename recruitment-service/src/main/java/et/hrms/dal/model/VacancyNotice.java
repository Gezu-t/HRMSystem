package et.hrms.dal.model;

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
@Table(name = "vacancy_notice")
public class VacancyNotice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String jobTitle;
  private String jobDescription;
  private Integer numberOfPositions;
  private LocalDate publishDate;
  private LocalDate closeDate;

  @OneToOne
  private Advertisement advertisement;

  @OneToMany(mappedBy = "vacancyNotice")
  private Collection<Recruitment> recruitment;
  public Collection<Recruitment> getRecruitment() {
    return recruitment;
  }
  public void setRecruitment(Collection<Recruitment> recruitment) {
    this.recruitment = recruitment;
  }
}
