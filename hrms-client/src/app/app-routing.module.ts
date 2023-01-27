import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { EmployeeComponent } from './components/employee/employee.component'
import { EducationComponent } from './components/education/education.component'
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { EmployeeRegistrationComponent } from './components/employee-registration/employee-registration.component';

const routes: Routes = [

  { path: 'employee', component: EmployeeComponent },
  { path: 'education', component: EducationComponent },
  { path: 'employee-list', component: EmployeeListComponent },
  { path: 'employee-details/:id', component: EmployeeDetailsComponent },
  { path: 'employee-registration', component: EmployeeRegistrationComponent },
  { path: '', redirectTo: '/employee-list', pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
