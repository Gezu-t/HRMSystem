package et.hrms.service;

import et.hrms.dal.dto.AddressDTO;
import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.model.EmployeeAddressManagement;

import java.util.List;
import java.util.Set;

public interface EmployeeAddressManagementService {
    void saveEmployeeWithAddress(EmployeeDTO employeeDTO, List<AddressDTO> addressDTOS);

    Set<EmployeeAddressManagement> getEmployeeAddressByEmployeeId(long employeeId);
}
