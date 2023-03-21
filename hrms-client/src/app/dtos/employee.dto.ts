import { EmployeeAddressDTO } from "./employee-address.dto";
import { EmployeeDetailDTO } from "./employee-detail.dto";

export class EmployeeDTO {
    employeeId: number | undefined;
    employeeNumber: string | undefined;
    firstName!: string;
    lastName!: string;
    dateOfBirth!: Date;
    dateOfJoining!: Date;
    genderStatus!: string;
    employeeAddressDTOS!: EmployeeAddressDTO[];
    employeeDetailDTOS!: EmployeeDetailDTO[];
  }

