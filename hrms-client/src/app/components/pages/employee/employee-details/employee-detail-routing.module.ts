import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { EmployeeDetailsComponent } from "./employee-details.component";





@NgModule({
	imports: [
		RouterModule.forChild([	
		{ 
            path: 'detail', component: EmployeeDetailsComponent

		}
		
	])],
	exports: [RouterModule]
})

export class EmployeeDetailRoutingModule {}