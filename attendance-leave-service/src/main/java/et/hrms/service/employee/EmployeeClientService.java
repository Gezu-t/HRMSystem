package et.hrms.service.employee;

import et.hrms.dal.dto.employee.EmployeeDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeClientService {

    private final RestTemplate restTemplate;
    private final String employeeServiceBaseUrl;

    @Autowired
    public EmployeeClientService(RestTemplate restTemplate,
                                 @Value("${employee.service.url}") String employeeServiceBaseUrl) {
        this.restTemplate = restTemplate;
        this.employeeServiceBaseUrl = employeeServiceBaseUrl;
    }

    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            return restTemplate.getForObject(employeeServiceBaseUrl + "/employees/{id}", EmployeeDTO.class, employeeId);
        } catch (HttpClientErrorException e) {
            throw new EntityNotFoundException("Employee is not found by this ID: " + employeeId);
        }
    }
}
