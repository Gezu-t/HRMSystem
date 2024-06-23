package com.hrmsystem.employeeprofileservice.dal.dto.job;


import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;

public class JobDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 9090627862183749387L;


    private Long jobId;
    @NotEmpty(message = "Job title must not be null")
    private String title;
    private Integer maxSalary;

    private Integer minSalary;

    private String description;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public @NotEmpty(message = "Job title must not be null") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Job title must not be null") String title) {
        this.title = title;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
