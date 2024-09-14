package com.hrmsystem.employeeservice.core.dal.dto.education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class EducationDetailDTO {
  private Long educationDetailId;
  private Long educationId;
  private String degree;
  private String major;
  private String minor;
  private String grade;
  private String gradePoint;
  private String gpa;
  private String gpaOutOf;
  private String gpaOutOfScale;
  private Year academicYear;
  private Long educationTypeId;

  public Long getEducationDetailId() {
    return educationDetailId;
  }

  public void setEducationDetailId(Long educationDetailId) {
    this.educationDetailId = educationDetailId;
  }

  public Long getEducationId() {
    return educationId;
  }

  public void setEducationId(Long educationId) {
    this.educationId = educationId;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public String getMinor() {
    return minor;
  }

  public void setMinor(String minor) {
    this.minor = minor;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getGradePoint() {
    return gradePoint;
  }

  public void setGradePoint(String gradePoint) {
    this.gradePoint = gradePoint;
  }

  public String getGpa() {
    return gpa;
  }

  public void setGpa(String gpa) {
    this.gpa = gpa;
  }

  public String getGpaOutOf() {
    return gpaOutOf;
  }

  public void setGpaOutOf(String gpaOutOf) {
    this.gpaOutOf = gpaOutOf;
  }

  public String getGpaOutOfScale() {
    return gpaOutOfScale;
  }

  public void setGpaOutOfScale(String gpaOutOfScale) {
    this.gpaOutOfScale = gpaOutOfScale;
  }

  public Year getAcademicYear() {
    return academicYear;
  }

  public void setAcademicYear(Year academicYear) {
    this.academicYear = academicYear;
  }

  public Long getEducationTypeId() {
    return educationTypeId;
  }

  public void setEducationTypeId(Long educationTypeId) {
    this.educationTypeId = educationTypeId;
  }
}
