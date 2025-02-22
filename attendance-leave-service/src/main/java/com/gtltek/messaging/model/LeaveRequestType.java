package com.gtltek.messaging.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "leave_request_type")
public class LeaveRequestType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 254)
  private String typeName;

  private String description;
}
