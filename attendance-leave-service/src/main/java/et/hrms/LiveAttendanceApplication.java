package et.hrms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "et.hrms.dal.model")
public class LiveAttendanceApplication {
    public static void main(String[] args) {

        SpringApplication.run(LiveAttendanceApplication.class, args);

    }
}