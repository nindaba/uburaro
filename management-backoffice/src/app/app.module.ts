import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ComponentsModule} from "./components/components.module";
import {LogoComponent} from "./components/logo/logo.component";
import {NotificationModule} from "./components/notification/notification.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ComponentsModule,
        LogoComponent,
        NotificationModule,

    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
