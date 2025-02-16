package com.hrms.employee.core.dal.model.job;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_level")
public class JobLevel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String levelName;
    private String levelPeriod;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Job> jobList;

}
