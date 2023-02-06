import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InputTextModule } from 'primeng/inputtext';
import { SidebarModule } from 'primeng/sidebar';
import { BadgeModule } from 'primeng/badge';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputSwitchModule } from 'primeng/inputswitch';
import { RippleModule } from 'primeng/ripple';
import { RouterModule } from '@angular/router';
import { AppConfigModule } from './config/config.module';
import { AppLayoutComponent } from "./app.layout.component";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { FooterComponent } from './footer/footer.component';
import { AppMenuitemComponent } from './menu/app.menuitem.component';
import { AppMenuComponent } from './menu/app.menu.component';
import { HeaderComponent } from './header/header.component';


@NgModule({
    declarations: [
        AppMenuitemComponent,
       HeaderComponent,
        AppMenuComponent,
        AppLayoutComponent,
        SidebarComponent,
        FooterComponent,
    ],
    exports: [AppLayoutComponent],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        InputTextModule,
        SidebarModule,
        BadgeModule,
        RadioButtonModule,
        InputSwitchModule,
        RippleModule,
        RouterModule,
        AppConfigModule,
       
    ]
})
export class AppLayoutModule { }
