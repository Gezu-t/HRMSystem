package com.hrmsystem.employeeprofileservice.dal.dto.job;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
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
