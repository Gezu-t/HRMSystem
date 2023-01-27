package et.hrms.service.impl;


import et.hrms.dal.dto.AppearanceDTO;
import et.hrms.dal.mapping.AppearanceMapper;
import et.hrms.dal.model.Appearance;
import et.hrms.dal.model.Employee;
import et.hrms.dal.repository.AppearanceRepository;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.AppearanceService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AppearanceServiceImpl implements AppearanceService {


    private final AppearanceMapper appearanceMapper;

    private final AppearanceRepository appearanceRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public void createEmployeeAppearance(Long employeeId, AppearanceDTO appearanceDTO) {
        if (appearanceDTO != null) {
            var appearance = appearanceMapper.toAppearance(appearanceDTO);

            var employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Employee is not found by this id:{0}", employeeId)));
            if (appearance != null) {
                appearance.setEmployee(employee);
                appearanceRepository.save(appearance);
            }
        } else {
            throw new EntityNotFoundException("Appearance is not found");
        }
    }

    @Override
    public AppearanceDTO getAppearanceById(long id) {
        Appearance appearance = appearanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appearance Info is not found by this id:" + id));

        return appearanceMapper.toAppearanceDTO(appearance);
    }


    @Override
    public AppearanceDTO updateAppearance(long employeeId, AppearanceDTO appearanceDTO) {
        var appearance = appearanceRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                        MessageFormat.format("Appearance information is not found by employee id{0}.", employeeId)));

        appearance.setId(appearanceDTO.getAppearanceId());
        appearance.setHeight(appearanceDTO.getHeight());
        appearance.setWeight(appearanceDTO.getWeight());
        appearance.setBloodGroup(appearanceDTO.getBloodGroup());
        appearance.setSkinColor(appearanceDTO.getSkinColor());
        appearance.setEyeColor(appearanceDTO.getEyeColor());
        Employee employee = new Employee();
        employee.setId(appearanceDTO.getEmployeeId());
        appearance.setEmployee(employee);

        return appearanceMapper.toAppearanceDTO(appearanceRepository.save(appearance));


    }

    @Override
    @SneakyThrows
    public List<AppearanceDTO> getAllAppearanceLists(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        CompletableFuture<Page<Appearance>> appearancesFuture = appearanceRepository.findAppearanceDetailAsync(pageable);

        Page<Appearance> appearancesPage = appearancesFuture.get();
        List<AppearanceDTO> appearancesList = appearancesPage.getContent().stream().map(appearanceMapper::toAppearanceDTO).toList();


        return appearancesList;
    }


}
