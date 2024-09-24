package com.hrmsystem.employeeservice.core.controller.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmsystem.employeeservice.core.controller.employee.imp.FamilyControllerImpl;
import com.hrmsystem.employeeservice.core.dal.dto.employee.FamilyDTO;
import com.hrmsystem.employeeservice.core.service.employee.FamilyService;
import dal.model.employee.Family;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FamilyControllerTest {
  private MockMvc mockMvc;

  @InjectMocks
  private FamilyControllerImpl familyController;

  @Mock
  private FamilyService familyService;

  private FamilyDTO familyDTO;
  private Family family;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(familyController).build();
    objectMapper = new ObjectMapper();

    familyDTO = new FamilyDTO();
    familyDTO.setId(1L);
    familyDTO.setNationality("Italy");
    familyDTO.setGender("M");
    familyDTO.setFamilyFirstName("Test one");
    familyDTO.setFamilyLastName("Test Two");
    familyDTO.setEmployeeId(1L);

    family = new Family();
    family.setId(1L);
  }

  @Test
  void testCreateFamily() throws Exception {
    Long employeeId = 1L;

    when(familyService.createEmployeeFamily(eq(employeeId), any(FamilyDTO.class))).thenReturn(familyDTO);

    mockMvc.perform(post("/api/families/{employeeId}", employeeId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(familyDTO)))
            .andExpect(status().isCreated());
  }

  @Test
  void testGetFamilyById() throws Exception {
    Long familyId = 1L;

    // Uncomment and fix this line if you want to test getFamilyById
    // when(familyService.getFamilyById(eq(familyId))).thenReturn(familyDTO);

    mockMvc.perform(get("/api/families/{familyId}", familyId)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void testGetAllFamilies() throws Exception {
    List<FamilyDTO> familyDTOS = new ArrayList<>();
    familyDTOS.add(familyDTO);

    given(familyService.getAllFamily(0, 10, Sort.by("id"))).willReturn(familyDTOS);

    mockMvc.perform(get("/api/families")
                    .param("page", "0")
                    .param("size", "10")
                    .param("sort", "id,asc"))
            .andExpect(status().isOk());
  }
}