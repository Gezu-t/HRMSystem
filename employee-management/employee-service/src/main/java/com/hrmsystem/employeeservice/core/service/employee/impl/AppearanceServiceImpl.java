package com.hrmsystem.employeeservice.core.service.employee.impl;


import dal.dto.employee.EmployeeAppearanceDTO;
import com.hrmsystem.employeeservice.core.dal.mapping.employee.EmployeeAppearanceMapper;
import com.hrmsystem.employeeservice.core.service.employee.AppearanceService;
import dal.model.employee.Employee;
import dal.model.employee.EmployeeAppearance;
import dal.repository.employee.AppearanceRepository;
import dal.repository.employee.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
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
public class AppearanceServiceImpl implements AppearanceService {

    private final EmployeeAppearanceMapper appearanceMapper;
    private final AppearanceRepository appearanceRepository;
    private final EmployeeRepository employeeRepository;

    public AppearanceServiceImpl(EmployeeAppearanceMapper appearanceMapper, AppearanceRepository appearanceRepository, EmployeeRepository employeeRepository) {
        this.appearanceMapper = appearanceMapper;
        this.appearanceRepository = appearanceRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void createEmployeeAppearance(Long employeeId, EmployeeAppearanceDTO employeeAppearanceDTO) {
        if (employeeAppearanceDTO != null) {
            var appearance = appearanceMapper.toAppearance(employeeAppearanceDTO);
            var employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Employee is not found by this id:{0}", employeeId)));
            if (appearance != null) {
                appearance.setEmployee(employee);
                appearanceRepository.save(appearance);
            }
        } else {
            throw new EntityNotFoundException("EmployeeAppearance is not found");
        }
    }

    @Override
    public EmployeeAppearanceDTO getAppearanceById(long id) {
        EmployeeAppearance employeeAppearance = appearanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EmployeeAppearance Info is not found by this id:" + id));

        return appearanceMapper.toAppearanceDTO(employeeAppearance);
    }


    @Override
    public EmployeeAppearanceDTO updateAppearance(long employeeId, EmployeeAppearanceDTO employeeAppearanceDTO) {
        var appearance = appearanceRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("EmployeeAppearance information is not found by employeeprofile id{0}.", employeeId)));

        appearance.setId(employeeAppearanceDTO.getId());
        appearance.setHeight(employeeAppearanceDTO.getHeight());
        appearance.setWeight(employeeAppearanceDTO.getWeight());
        appearance.setBloodGroup(employeeAppearanceDTO.getBloodGroup());
        appearance.setSkinColor(employeeAppearanceDTO.getSkinColor());
        appearance.setEyeColor(employeeAppearanceDTO.getEyeColor());
        Employee employee = new Employee();
        employee.setId(employeeAppearanceDTO.getEmployeeId());
        appearance.setEmployee(employee);

        return appearanceMapper.toAppearanceDTO(appearanceRepository.save(appearance));


    }

    @Override
    @SneakyThrows
    public List<EmployeeAppearanceDTO> getAllAppearanceLists(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        CompletableFuture<Page<EmployeeAppearance>> appearancesFuture = appearanceRepository.findAppearanceDetailAsync(pageable);

        Page<EmployeeAppearance> appearancesPage = appearancesFuture.get();


        return appearancesPage.getContent().stream().map(appearanceMapper::toAppearanceDTO).toList();
    }


}
