package et.hrms.controller.impl;


import et.hrms.controller.AddressController;
import et.hrms.service.EmployeeAddressManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressControllerImpl implements AddressController {

    private final EmployeeAddressManagementService employeeAddressManagementService;
}
