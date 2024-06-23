package com.hrmsystem.employeeservice.core.dal.model.education;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "education_level")
public class EducationLevel implements Serializable {
  @Serial
  private static final long serialVersionUID = -7058728075209654201L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String educationLevelName;
  @OneToMany(mappedBy = "educationLevel")
  private List<Education> educations;

  private LocalDateTime updateAt;
  private LocalDateTime createdAt;

}
