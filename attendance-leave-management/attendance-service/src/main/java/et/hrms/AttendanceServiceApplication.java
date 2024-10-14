package et.hrms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@ComponentScan(basePackages = "et.hrms.dal.model")
public class AttendanceServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(AttendanceServiceApplication.class, args);
    }
}