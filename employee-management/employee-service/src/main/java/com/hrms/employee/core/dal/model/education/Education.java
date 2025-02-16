package com.hrms.employee.core.dal.model.education;

import com.hrms.employee.core.dal.model.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "education")
public class Education implements Serializable {

  @Serial
  private static final long serialVersionUID = -968845234787925272L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
