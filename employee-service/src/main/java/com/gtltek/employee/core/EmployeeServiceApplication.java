package com.gtltek.employee.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hrms.employee.core.messaging.repository")
@EntityScan(basePackages = "com.hrms.employee.core.messaging.model")
@ComponentScan(basePackages = {
        "com.hrms.employee.core.messaging.repository",
        "com.hrms.employee.core.messaging.model",
        "com.hrms.employee.core.messaging.dto"
})
public class EmployeeServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmployeeServiceApplication.class, args);
  }

}
