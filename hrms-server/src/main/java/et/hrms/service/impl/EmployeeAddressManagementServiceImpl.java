package et.hrms.service.impl;


import et.hrms.dal.dto.AddressDTO;
import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.model.EmployeeAddressManagement;
import et.hrms.dal.repository.AddressRepository;
import et.hrms.dal.repository.EmployeeAddressManageRepository;
import et.hrms.dal.repository.EmployeeRepository;
import et.hrms.service.EmployeeAddressManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeAddressManagementServiceImpl implements EmployeeAddressManagementService {
    private final EmployeeAddressManageRepository employeeAddressManageRepository;

    private final AddressRepository addressRepository;

    private final EmployeeRepository employeeRepository;




    @Override
    public void saveEmployeeWithAddress(EmployeeDTO employeeDTO, List<AddressDTO> addressDTOS) {



    }


    @Override
    public Set<EmployeeAddressManagement> getEmployeeAddressByEmployeeId(long employeeId){
        return employeeAddressManageRepository.findByEmployeeId(employeeId);
    }
}
