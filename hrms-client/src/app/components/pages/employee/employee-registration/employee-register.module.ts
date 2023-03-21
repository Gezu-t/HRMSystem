import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { InputMaskModule } from 'primeng/inputmask';
import { InputNumberModule } from 'primeng/inputnumber';

import { InputTextareaModule } from 'primeng/inputtextarea';
import { InputTextModule } from 'primeng/inputtext';
import { ListboxModule } from 'primeng/listbox';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CheckboxModule } from 'primeng/checkbox';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { TabViewModule } from 'primeng/tabview';
import { RadioButtonModule } from 'primeng/radiobutton';
import { EmployeeRegisterComponent } from './employee-register.component';
import { EmployeeRegisterRoutingModule } from './employee-register-routing.module';

import { ImageModule } from 'primeng/image';

import { FileUploadModule } from 'primeng/fileupload';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
    imports: [
        FileUploadModule,
        HttpClientModule,
        FormsModule,
        EmployeeRegisterRoutingModule,
        AutoCompleteModule,
        CalendarModule,
        DropdownModule,
        InputMaskModule,
        InputNumberModule,
        ImageModule,
        TableModule,
        CommonModule,
        ReactiveFormsModule,
        InputTextareaModule,
        RadioButtonModule,
        InputTextModule,

        ListboxModule,
        SelectButtonModule,
        CheckboxModule,

        TabViewModule,
        ButtonModule,
    ],
    declarations: [EmployeeRegisterComponent],
})
export class EmployeeRegisterModule {}
