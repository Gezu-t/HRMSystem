package education.service.education;

import et.hrms.dal.dto.EducationDTO;
import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.model.Employee;
import et.hrms.dal.model.EmployeeEducationManagement;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.service.EmployeeEducationManagementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeEducationManagementServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;
    // Create an instance of the EducationService implementation class


    @Mock
    private EmployeeEducationManagementService employeeEducationManagementService;


    @Test
    public void testUpdateEmployeeWithEducations() {
        // Create a test employee and education
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(1L); // set the employee id to 1
        employeeDTO.setFirstName("John Smith");
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setEducationId(1L); // set the education id to 1
        educationDTO.setDegree("Bachelor's Degree");

        // Add the education to the employee's education list
        List<EducationDTO> educationDTOS = new ArrayList<>();
        educationDTOS.add(educationDTO);

        // Call the updateEmployeeWithEducations method
        employeeEducationManagementService.updateEmployeeWithEducations(employeeDTO, educationDTOS);

        // Retrieve the updated employee from the database
        Employee updatedEmployee = employeeRepository.findById(1L).orElse(null);

        // Assert that the employee's name was updated
        Set<EmployeeEducationManagement> employeeEducationManagements = null;
        if (updatedEmployee != null) {
            assertEquals("John Smith", updatedEmployee.getFirstName());
            employeeEducationManagements = updatedEmployee.getEmployeeEducationManagements();
            assertEquals(1, employeeEducationManagements.size());
            // Assert that the employee's education list contains the correct education
            EmployeeEducationManagement employeeEducationManagement = employeeEducationManagements.iterator().next();
            assertEquals(Optional.of(1L), employeeEducationManagement.getEducation().getId());
            assertEquals("Bachelor's Degree", employeeEducationManagement.getEducation().getDegree());
        }


    }

}
