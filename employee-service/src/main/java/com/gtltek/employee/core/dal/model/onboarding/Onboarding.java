package com.gtltek.employee.core.dal.model.onboarding;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_onboarding")
public class Onboarding implements Serializable {

  @Serial
  private static final long serialVersionUID = 7612942408711273350L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate firstDate;

  private Boolean Status;

}
