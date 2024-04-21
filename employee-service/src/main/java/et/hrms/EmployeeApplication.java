package et.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("et.hrms.dal.model")
//@OpenAPIDefinition(info = @Info(title = "hrms api", version = "1.0.0", description = "Human Resource Management System"))
public class EmployeeApplication {

  public static void main(String[] args) {

    SpringApplication.run(EmployeeApplication.class, args);
  }

}
