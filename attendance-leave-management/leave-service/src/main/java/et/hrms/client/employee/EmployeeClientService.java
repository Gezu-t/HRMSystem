package et.hrms.client.employee;

import dal.dto.employee.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClientService {

    @GetMapping("/api/v1/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Long employeeId);
}
