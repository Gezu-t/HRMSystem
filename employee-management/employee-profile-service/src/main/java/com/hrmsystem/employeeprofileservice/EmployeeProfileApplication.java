package com.hrmsystem.employeeprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.hrmsystem.employeeprofileservice.dal.repository","dal.repository"})
@EntityScan(basePackages = {"com.hrmsystem.employeeprofileservice.dal.model","dal.model"})
@ComponentScan(basePackages = {
        "com.hrmsystem.employeeprofileservice",
        "dal.repository",
        "dal.model"
})
@EnableFeignClients
public class EmployeeProfileApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmployeeProfileApplication.class, args);
  }

}
