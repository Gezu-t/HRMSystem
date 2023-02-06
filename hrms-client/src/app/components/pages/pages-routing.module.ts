import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';



const routes:Routes = [
  { path: 'register',  loadChildren: () => import('./employee/employee-registration/employee-register.module').then(m => m.EmployeeRegisterModule) },
        { path: 'list',  loadChildren: () => import('./employee/employee-list/employee-list.module').then(m => m.EmployeeListModule) },
        { path: 'detail', loadChildren: () => import('./employee/employee-details/employee-detail.module').then(m => m.EmployeeDetailModule) },
        { path: 'edit', loadChildren: () => import('./employee/employee-edit/employee-edit.module').then(m => m.EmployeeEditModule) },
       
        { path: 'education', loadChildren: () => import('./education/education.module').then(m => m.EducationModule) },
        // { path: 'timeline', loadChildren: () => import('./timeline/timelinedemo.module').then(m => m.TimelineDemoModule) },
        { path: '**', redirectTo: '/notfound' }

];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
  })
export class PagesRoutingModule { }


