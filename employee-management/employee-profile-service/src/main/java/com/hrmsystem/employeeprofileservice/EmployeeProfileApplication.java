package com.hrmsystem.employeeprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.hrmsystem.employeeprofileservice", "com.hrmsystem.employeeservice.core.dal.repository"})
public class EmployeeProfileApplication {

  public static void main(String[] args) {

    SpringApplication.run(EmployeeProfileApplication.class, args);
  }

}
