import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AppLayoutModule } from './layout/app.layout.module';
import { PhotoService } from './services/photo.service';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { CountryService } from './services/country.service';


@NgModule({
  declarations: [
    AppComponent,
    NotfoundComponent, 
  ],
  imports: [
    AppRoutingModule,
    AppLayoutModule
  
  ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy},
    CountryService, PhotoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
