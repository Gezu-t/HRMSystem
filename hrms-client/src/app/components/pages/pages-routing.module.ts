import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    {
        path: 'employee-register',
        loadChildren: () =>
            import('./employee/employee-registration/employee-register.module').then(
                (m) => m.EmployeeRegisterModule),
    },
    {
        path: 'employee-list',
        loadChildren: () =>
            import('./employee/employee-list/employee-list.module').then(
                (m) => m.EmployeeListModule),
    },
    {
        path: 'employee-detail',
        loadChildren: () =>
            import('./employee/employee-details/employee-detail.module').then(
                (m) => m.EmployeeDetailModule),
    },
    {
        path: 'employee-edit',
        loadChildren: () =>
            import('./employee/employee-edit/employee-edit.module').then(
                (m) => m.EmployeeEditModule),
    },

    {
        path: 'education',
        loadChildren: () =>
            import('./education/education.module').then(
                (m) => m.EducationModule
            ),
    },
    { path: 'employee-register', loadChildren: () => 
            import('./employee/employee-registration/employee-register.module').then(
                (m) => m.EmployeeRegisterModule) 
            },
    { path: '**', redirectTo: '/notfound' },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PagesRoutingModule {}
