package com.hrms.employee.core.dal.model.onboarding;

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
@Table(name = "material_assigned")
public class MaterialAssigned {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate assignedDate;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "materialTypeId")
  private MaterialType materialType;
  private String materialSpecification;
  private String serialCode;
  private String model;
  private LocalDate manufacturingDate;
  private String statusOfMaterial;

  private LocalDate overDueDate;
  @Enumerated(EnumType.STRING)
  private MaterialAssignedStatus materialAssignedStatus;
  private LocalDate returnDate;
}
