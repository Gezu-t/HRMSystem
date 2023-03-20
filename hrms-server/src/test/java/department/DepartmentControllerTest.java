package department;


import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setup() {
        // Prepare any necessary setup for the tests
    }

    @Test
    public void testGetDepartmentById() throws Exception {
        // Prepare test data and mock interactions
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(1L);

        when(departmentService.getDepartmentById(anyLong())).thenReturn(departmentDTO);

        // Perform the request
        mockMvc.perform(get("/api/department/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentId").value(1L));

        // Verify the interactions
        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    // Add tests for other endpoints in the DepartmentController
}
