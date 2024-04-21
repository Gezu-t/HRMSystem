package et.hrms.dal.model.attendance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "absentee_reported_by")
  private String absenteeReportedBy;

  @Column(name = "absentee_date")
  private LocalDate absenteeDate;

  @Column(name = "absentee_description")
  private String absenteeDescription;

  @Column(name = "recorded_by")
  private String recordedBy;

  @Column(name = "recorded_date")
  private LocalDate recordedDate;

  @Column(name = "attendance_state")
  private String attendanceState;

  @Column(name = "for_department")
  private String forDepartment;

  @Enumerated(EnumType.STRING)
  @Column(name = "attendance_status")
  private AttendanceStatus attendanceStatus;

  @Column(name = "employee_id")
  private Long employeeId;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

}
