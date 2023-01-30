import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/app/services/EmployeeService';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {
 
  employees = [
    { id: 1, name: 'John Doe',  email: 'g@gmail.com' },
    { id: 2, name: 'Jane Smith',  email: 'g@gmail.com' },
    { id: 3, name: 'Bob Johnson', email: 'g@gmail.com'},
  ];


  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {
    this.employeeService.getEmployees()
    .subscribe( data => {
      this.employees = data;
    });
  }



  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id)
    .subscribe( data => {
      this.employeeService.getEmployees()
      .subscribe( data => {
        this.employees = data;
      });
    });
  }

}
