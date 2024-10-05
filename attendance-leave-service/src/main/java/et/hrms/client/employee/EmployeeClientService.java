package et.hrms.client.employee;

import dal.dto.employee.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EmployeeClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${employeeprofile.service.url}")
    private String employeeServiceUrl;

    public EmployeeClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            String url = employeeServiceUrl + "/employees/" + employeeId;
            return restTemplate.getForObject(url, EmployeeDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve employeeprofile: " + employeeId, e);
        }
    }

    public EmployeeDTO[] getAllEmployees() {
        try {
            return restTemplate.getForObject(employeeServiceUrl + "/employees", EmployeeDTO[].class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all employees", e);
        }
    }

//    public EmployeeDTO[] searchEmployeesByName(String name) {
//        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(employeeServiceUrl + "/employees/search")
//                .queryParam("name", name);
//        return restTemplate.getForObject(uriBuilder.toUriString(), EmployeeDTO[].class);
//    }

    public EmployeeDTO[] searchEmployeesByName(String name) {
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(employeeServiceUrl + "/employees/search")
                    .queryParam("name", name);
            return restTemplate.getForObject(uriBuilder.toUriString(), EmployeeDTO[].class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return new EmployeeDTO[0];  // Return an empty array if no employees are found
            }
            throw new RuntimeException("Error while fetching employees: " + e.getMessage());
        } catch (HttpServerErrorException e) {
            throw new RuntimeException("Server error occurred: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to fetch employees: " + e.getMessage());
        }
    }
}

