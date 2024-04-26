package et.hrms.client.employee;

import et.hrms.dal.dto.employee.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${employee.service.url}")
    private String employeeServiceUrl;

    public EmployeeClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            String url = employeeServiceUrl + "/employees/" + employeeId;
            return restTemplate.getForObject(url, EmployeeDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve employee: " + employeeId, e);
        }
    }

    public EmployeeDTO[] getAllEmployees() {
        try {
            return restTemplate.getForObject(employeeServiceUrl + "/employees", EmployeeDTO[].class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all employees", e);
        }
    }
}

