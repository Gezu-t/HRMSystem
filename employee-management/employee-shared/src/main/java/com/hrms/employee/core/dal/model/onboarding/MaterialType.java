package com.hrms.employee.core.dal.model.onboarding;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "material_type")
public class MaterialType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "material_type_name", unique = true)
  private String name;
  private String description;

}
