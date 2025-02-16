package com.hrms.employee.core.dal.dto.job;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobLevelDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1851672278041228469L;


    private Long jobLevelId;
    private String jobLevelName;


    public Long getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Long jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }
}
