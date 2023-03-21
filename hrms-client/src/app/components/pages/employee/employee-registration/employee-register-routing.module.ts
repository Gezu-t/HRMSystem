import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { EmployeeRegisterComponent } from "./employee-register.component";




@NgModule({
	imports: [
		RouterModule.forChild([
		{ 
			path: '', component: EmployeeRegisterComponent
		}
		
	])],
	exports: [RouterModule]
})

export class EmployeeRegisterRoutingModule {}