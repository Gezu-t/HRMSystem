package et.hrms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("et.hrms.dal.model")
@OpenAPIDefinition(info = @Info(title = "hrms api", version = "1.0.0", description = "Human Resource Management System"))
//@EnableCaching
public class HrmsBeApplication {

  public static void main(String[] args) {

    SpringApplication.run(HrmsBeApplication.class, args);
  }

/*
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

*/

}
