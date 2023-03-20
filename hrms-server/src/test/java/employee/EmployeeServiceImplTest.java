package employee;


import et.hrms.dal.model.Employee;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.service.EmployeeService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    public void TestCreateEmployeeOk() {

        Employee employee = new Employee();


    }

}
