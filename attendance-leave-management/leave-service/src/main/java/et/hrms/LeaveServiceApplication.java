package et.hrms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@ComponentScan(basePackages = "et.hrms.com.hrms.employee.core.dal.model")
public class LeaveServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeaveServiceApplication.class, args);
    }
}