import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators  } from '@angular/forms';
import { EmployeeDTO } from 'src/app/dtos/employee.dto';
import { EmployeeService } from 'src/app/services/employee.service';
import { EmployeeAddressDTO } from 'src/app/dtos/employee-address.dto';
import { EmployeeDetailDTO } from 'src/app/dtos/employee-detail.dto';
import { DepartmentDTO } from 'src/app/dtos/department.dto';

@Component({
    templateUrl: './employee-register.component.html',
    styleUrls: ['./employee-register.component.scss'],
})
export class EmployeeRegisterComponent implements OnInit {

    employeeForm: FormGroup;
    index = 0; // define index property

    constructor(private fb: FormBuilder, private employeeService: EmployeeService) { }

    ngOnInit(): void {
      this.employeeForm = this.fb.group({
        employeeNumber: [''],
        firstName: [''],
        lastName: [''],
        dateOfBirth: [''],
        dateOfJoining: [''],
        employeeAddressDTOS: this.fb.array([]),
        employeeDetailDTOS: this.fb.array([]),
      });
    }

    addEmployeeAddress(): void {
      const employeeAddressFormGroup = this.fb.group({
        telNumberHome: [''],
        telNumberOffice: [''],
        mobile: [''],
        houseNumber: [''],
        street: [''],
        streetNumber: [''],
        region: [''],
        province: [''],
        city: [''],
        postalCode: [''],
        country: [''],
      });
      this.employeeAddressDTOS.push(employeeAddressFormGroup);
      this.index++; // increment index
    }

    removeEmployeeAddress(index: number): void {
      this.employeeAddressDTOS.removeAt(index);
      this.index--; // decrement index
    }

    get employeeAddressDTOS(): FormArray {
      return this.employeeForm.get('employeeAddressDTOS') as FormArray;
    }

    addEmployeeDetail(): void {
      const employeeDetailFormGroup = this.fb.group({
        departmentDTO: this.fb.group({
          departmentId: [''],
          departmentName: [''],
          departmentCode: [''],
        }),
      });
      this.employeeDetailDTOS.push(employeeDetailFormGroup);
      this.index++; // increment index
    }

    removeEmployeeDetail(index: number): void {
      this.employeeDetailDTOS.removeAt(index);
      this.index--; // decrement index
    }

    get employeeDetailDTOS(): FormArray {
      return this.employeeForm.get('employeeDetailDTOS') as FormArray;
    }

    onSubmit(): void {
      const employeeDTO: EmployeeDTO = this.employeeForm.value;
     // employeeDTO.employeeAddressDTOS = employeeDTO.employeeAddressDTOS.map((address: any) => new EmployeeAddressDTO(address));
//       employeeDTO.employeeDetailDTOS = employeeDTO.employeeDetailDTOS.map((detail: any) => new EmployeeDetailDTO(detail));
      this.employeeService.createEmployee(employeeDTO).subscribe(
        (response) => console.log(response),
        (error) => console.log(error)
      );
    }

  }
