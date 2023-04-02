package et.hrms.dal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "advertisement", schema = "public")
public class Advertisement {

  @Id
  @SequenceGenerator(name = "advertisement_id_gen",
          sequenceName = "advertisement_id_seq",
          allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "advertisement_id_gen")
  private Long id;

  private String title;
  private String media;
  private LocalDate publishDate;

  @OneToOne(mappedBy = "advertisement")
  private VacancyNotice vacancyNotice;

  // Getters and setters
}

