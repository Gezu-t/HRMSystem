import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { EmployeeEditFormComponent } from "./employee-edit.component";



@NgModule({
	imports: [
		RouterModule.forChild([
		{ 
			path: '', component: EmployeeEditFormComponent
		}
		
	])],
	exports: [RouterModule]
})

export class EmployeeEditRoutingModule {}