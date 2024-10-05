package com.hrmsystem.employeeservice.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "dal.repository")
@EntityScan(basePackages = "dal.model")
@ComponentScan(basePackages = {
        "com.hrmsystem.employeeservice",
        "dal.repository",
        "dal.model",
        "dal.dto"
})
public class EmployeeServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmployeeServiceApplication.class, args);
  }

}
