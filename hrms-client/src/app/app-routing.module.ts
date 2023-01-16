import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { EmployeeFormComponent } from './employee-form/employee-form.component'
import { EducationFormComponent } from './education-form/education-form.component'

const routes: Routes = [

    {
      path: '',
       redirectTo: '/home',
          pathMatch: 'full'
        },
        {
          path: 'Employee',
          component: EmployeeFormComponent,
        },
        {
          path: 'Education',
          component: EducationFormComponent,
        },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
