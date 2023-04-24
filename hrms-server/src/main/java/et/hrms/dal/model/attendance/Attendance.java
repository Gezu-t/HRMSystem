package et.hrms.dal.model.attendance;

import et.hrms.dal.model.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "attendance")
public class Attendance {

  @Id
  @SequenceGenerator(name = "attendance_id_gen", sequenceName = "attendance_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_id_gen")
  private Long id;
  private String absenteeReportedBy;
  private LocalDate absenteeDate;
  private String absenteeDescription;
  private String recordedBy;
  private LocalDate recordedDate;
  private String attendanceState;
  private String forDepartment;

  @Enumerated(EnumType.STRING)
  @Column(name="attendance_status")
  private AttendanceStatus attendanceStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employeeId")
  private Employee employee;

  private LocalDateTime createdAt;
  private LocalDateTime updateAt;





}
