import {APP_INITIALIZER, NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AppLayoutModule } from './layout/app.layout.module';
import { PhotoService } from './services/photo.service';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { CountryService } from './services/country.service';
import { environment } from '../environments/environment';



export function initializeApp() {
  return () => {
    return new Promise<void>((resolve, reject) => {
      const xhr = new XMLHttpRequest();
      xhr.open('GET', 'assets/environment.json');
      xhr.onload = () => {
        if (xhr.status === 200) {
          const environment = JSON.parse(xhr.responseText);
          resolve();
        } else {
          reject();
        }
      };
      xhr.onerror = () => {
        reject();
      };
      xhr.send();
    });
  };
}

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
    CountryService, PhotoService,
    { provide: APP_INITIALIZER, useFactory: initializeApp, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
