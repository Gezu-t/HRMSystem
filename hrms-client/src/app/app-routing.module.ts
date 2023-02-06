import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';



import { AppLayoutComponent } from "./layout/app.layout.component";
import { NotfoundComponent } from './components/notfound/notfound.component';

const routes: Routes = [

  {
    path: '', component: AppLayoutComponent,
    children: [
        { path: '', loadChildren: () => import('./components/dashboard/dashboard.module').then(m => m.DashboardModule) },
        { path: 'pages', loadChildren: () => import('./components/pages/pages.module').then(m => m.PagesModule) }     
    ]
  },

  { path: 'auth', loadChildren: () => import('./components/auth/auth.module').then(m => m.AuthModule) },
  { path: 'landing', loadChildren: () => import('./components/landing/landing.module').then(m => m.LandingModule) },
  { path: 'notfound', component: NotfoundComponent },
  { path: '**', redirectTo: '/notfound' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
