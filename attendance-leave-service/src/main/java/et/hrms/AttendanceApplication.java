package et.hrms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "et.hrms.dal.model")
public class AttendanceApplication {
    public static void main(String[] args) {

        SpringApplication.run(AttendanceApplication.class, args);

    }
}