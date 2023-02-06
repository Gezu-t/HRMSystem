import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { EmployeeRegistrationComponent } from "./employee-registration.component";




@NgModule({
	imports: [
		RouterModule.forChild([
		{ 
			path: 'registration', component: EmployeeRegistrationComponent
		}
		
	])],
	exports: [RouterModule]
})

export class EmployeeRegisterRoutingModule {}