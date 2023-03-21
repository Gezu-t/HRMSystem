import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { EmployeeListComponent } from './employee-list.component';
import { EmployeeListRoutingModule } from './employee-list-routing.module';




@NgModule({
	imports: [
		CommonModule,
		FormsModule,
		EmployeeListRoutingModule
		
	],
	declarations: [
        EmployeeListComponent
    ]
})

export class EmployeeListModule {}