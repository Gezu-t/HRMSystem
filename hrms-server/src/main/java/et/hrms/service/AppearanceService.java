package et.hrms.service;

import et.hrms.dal.dto.AppearanceDTO;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AppearanceService {

    void createEmployeeAppearance(Long employeeId, AppearanceDTO appearanceDTO);

    AppearanceDTO getAppearanceById(long id);

    AppearanceDTO updateAppearance(long appearanceId, AppearanceDTO appearanceDTO);

    @SneakyThrows
    List<AppearanceDTO> getAllAppearanceLists(int page, int size, Sort sort);
}
