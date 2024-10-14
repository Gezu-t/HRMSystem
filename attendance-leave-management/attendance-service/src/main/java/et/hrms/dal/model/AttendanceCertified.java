package et.hrms.dal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "attendance_certified")
public class AttendanceCertified {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "certified_by", unique = true)
  private String certifiedBy;
  private LocalDate certifiedDate;
  @Enumerated(EnumType.STRING)
  private AttendanceCertifiedStatus certifyStatus;
  @Column(name = "description", length = 254, nullable = true)
  private String description;
}
