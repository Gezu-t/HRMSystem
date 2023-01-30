package et.hrms.service;

import et.hrms.dal.dto.EmployeeAppearanceDTO;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AppearanceService {

    void createEmployeeAppearance(Long employeeId, EmployeeAppearanceDTO employeeAppearanceDTO);

    EmployeeAppearanceDTO getAppearanceById(long id);

    EmployeeAppearanceDTO updateAppearance(long appearanceId, EmployeeAppearanceDTO employeeAppearanceDTO);

    @SneakyThrows
    List<EmployeeAppearanceDTO> getAllAppearanceLists(int page, int size, Sort sort);
}
