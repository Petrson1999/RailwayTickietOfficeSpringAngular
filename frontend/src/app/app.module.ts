import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HeaderComponent} from "./components/header/header.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {
  BsDatepickerModule, BsDropdownModule, CarouselModule,
  CollapseModule,
  ModalModule,
  ProgressbarModule,
  TabsModule, TimepickerModule,
  TooltipModule
} from "ngx-bootstrap";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgxBootstrapSliderModule} from "ngx-bootstrap-slider";
import {ToastrModule} from "ngx-toastr";
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "./routing/app-routing.module";
import {appRoutes} from "./routing/routes";
import {AuthInterceptor} from "./auth/auth.interceptor";
import {LandingComponent} from "./components/landing/landing.component";
import { TicketsComponent } from './components/tickets/tickets.component';
import {SelectDropDownModule} from "ngx-select-dropdown";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LandingComponent,
    TicketsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CollapseModule.forRoot(),
    BrowserAnimationsModule,
    NgxBootstrapSliderModule,
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot(),
    TabsModule.forRoot(),
    ToastrModule.forRoot({
      preventDuplicates : false
    }),
    RouterModule.forRoot(appRoutes),
    ProgressbarModule.forRoot(),
    TooltipModule.forRoot(),
    BsDropdownModule.forRoot(),
    AppRoutingModule,
    FormsModule,
    CarouselModule.forRoot(),
    SelectDropDownModule,
    TimepickerModule.forRoot(),
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
