package family;

import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.dto.FamilyDTO;
import et.hrms.dal.mapping.FamilyMapper;
import et.hrms.dal.model.Employee;
import et.hrms.dal.model.Family;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.dal.repository.FamilyRepository;
import et.hrms.service.impl.FamilyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FamilyServiceTest {

  @Mock
  private FamilyRepository familyRepository;
  @Mock
  private EmployeeRepository employeeRepository;
  @Mock
  private FamilyMapper familyMapper;
  @InjectMocks
  private FamilyServiceImpl familyService;

  private FamilyDTO familyDTO;
  private Family family;
  private EmployeeDTO employeeDTO;
  private Employee employee;

  @Before
  public void setUp() {
    familyDTO = new FamilyDTO();
    familyDTO.setFamilyId(1L);
    familyDTO.setNationality("Italy");
    familyDTO.setGender("M");
    familyDTO.setFamilyFirstName("Test one");
    familyDTO.setFamilyLastName("Test Two");
    familyDTO.setEmployeeId(1L);
    family = new Family();
    family.setId(1L);
    employeeDTO = new EmployeeDTO();
    employeeDTO.setEmployeeId(1L);
    employee = new Employee();
    employee.setId(1L);
  }

  @Test
  public void testCreateFamily() {
    Long employeeId = 1L;
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.ofNullable(employee));
    when(familyMapper.toFamily(familyDTO)).thenReturn(family);
    when(familyRepository.save(family)).thenReturn(family);
    when(familyMapper.toFamilyDTO(family)).thenReturn(familyDTO);
    FamilyDTO createdFamilyDTO = familyService.createEmployeeFamily(employeeId, familyDTO);
    assertEquals(familyDTO, createdFamilyDTO);
    verify(employeeRepository, times(1)).findById(employeeId);
    verify(familyMapper, times(1)).toFamily(familyDTO);
    verify(familyRepository, times(1)).save(family);
    verify(familyMapper, times(1)).toFamilyDTO(family);
  }

  @Test
  public void testGetFamilyById() {
    Long familyId = 1L;

    when(familyRepository.findById(familyId)).thenReturn(Optional.of(family));
    when(familyMapper.toFamilyDTO(family)).thenReturn(familyDTO);

    FamilyDTO foundFamilyDTO = familyService.getFamilyById(familyId);

    assertEquals(familyDTO, foundFamilyDTO);
    verify(familyRepository, times(1)).findById(familyId);
    verify(familyMapper, times(1)).toFamilyDTO(family);
  }

  @Test
  public void testGetAllFamily() {
    int page = 0;
    int size = 10;
    Sort sort = Sort.by(Sort.Direction.ASC, "id");
    Pageable pageable = PageRequest.of(page, size, sort);
    List<Family> familyList = Stream.of(new Family(), new Family(), new Family()).collect(Collectors.toList());
    Page<Family> familyPage = new PageImpl<>(familyList, pageable, familyList.size());
    List<FamilyDTO> familyDTOList = familyList.stream().map(f -> new FamilyDTO()).collect(Collectors.toList());

    when(familyRepository.findAll(pageable)).thenReturn(familyPage);
    when(familyMapper.toFamilyDTO(any(Family.class))).thenReturn(new FamilyDTO());

    List<FamilyDTO> result = familyService.getAllFamily(page, size, sort);

    assertEquals(familyDTOList.size(), result.size());
    verify(familyRepository, times(1)).findAll(pageable);
    verify(familyMapper, times(familyList.size())).toFamilyDTO(any(Family.class));
  }


}
