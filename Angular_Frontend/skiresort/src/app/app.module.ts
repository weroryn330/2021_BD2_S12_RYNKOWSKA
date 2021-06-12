import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule} from "@angular/forms";
import { HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {authInterceptorProviders} from "./interceptors/authorization.interceptor";


import { AppComponent } from './app.component';
import { ProfileComponent} from "./components/profile/profile.component";
import { PurchaseComponent} from "./components/purchase/purchase.component";
import { PricelistComponent} from "./components/pricelist/pricelist.component";
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { LoginComponent } from './components/login/login.component';
import { ContactComponent } from './components/contact/contact.component';
import { LiftsComponent } from './components/lifts/lifts.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { PassFormComponent } from './components/pass-form/pass-form.component';
import { UserCredentialsComponent } from './components/user-credentials/user-credentials.component';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { UserPassesComponent } from './components/user-passes/user-passes.component';
import { UserReportComponent } from './components/user-report/user-report.component';
import { UserEditInfoComponent } from './components/user-edit-info/user-edit-info.component';
import { UserEditCredentialsComponent } from './components/user-edit-credentials/user-edit-credentials.component';
import { ActivePassesComponent } from './components/employee/active-passes/active-passes.component';
@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    PurchaseComponent,
    PricelistComponent,
    WelcomePageComponent,
    LoginComponent,
    ContactComponent,
    LiftsComponent,
    RegistrationComponent,
    PassFormComponent,
    UserCredentialsComponent,
    UserInfoComponent,
    UserPassesComponent,
    UserReportComponent,
    UserEditInfoComponent,
    UserEditCredentialsComponent,
    ActivePassesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
