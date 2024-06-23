package com.hrmsystem.employeeservice.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = {"com.hrmsystem.employeeservice.core.dal.model"})
@EnableJpaRepositories(basePackages = {"com.hrmsystem.employeeservice.core.dal.repository"})
public class EmployeeApplication {

  public static void main(String[] args) {

    SpringApplication.run(EmployeeApplication.class, args);
  }

}
