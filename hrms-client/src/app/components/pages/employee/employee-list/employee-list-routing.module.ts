import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { EmployeeListComponent } from "./employee-list.component";




@NgModule({
	imports: [
		RouterModule.forChild([
		
		{ 
            path: 'list', component: EmployeeListComponent

		}
		
	])],
	exports: [RouterModule]
})

export class EmployeeListRoutingModule {}