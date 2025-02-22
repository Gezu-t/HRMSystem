package com.gtltek.messaging.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "attendance_approve")
public class AttendanceApprove {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String approvedBy;
  private LocalDate approvedDate;
  private String description;
}
