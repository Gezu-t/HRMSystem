package et.hrms.dal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {

  @Serial
  private static final long serialVersionUID = 4784327991784793970L;


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

  @Column(name = "start_time")
  private LocalDateTime startTime;

  @Column(name = "end_time")
  private LocalDateTime endTime;

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

  // Method to calculate working hours for this attendance entry
  public double calculateWorkingHours() {
    if (startTime != null && endTime != null) {
      return Duration.between(startTime, endTime).toMinutes() / 60.0;
    }
    return 0.0;
  }

}
