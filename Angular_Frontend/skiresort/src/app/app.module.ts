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
import { UserInvoicesComponent } from './components/user-invoices/user-invoices.component';
import { ManagementComponent } from './components/management/management.component';
import { LiftManagementComponent } from './components/lift-management/lift-management.component';
import { SchedulesComponent } from './components/employee/schedules/schedules.component';
import { PricelistEditComponent } from './components/employee/pricelist-edit/pricelist-edit.component';
import { EmployeeAddComponent } from './components/employee/employee-add/employee-add.component';
import { EmployeeEditComponent } from './components/employee/employee-edit/employee-edit.component';
import { UsersListComponent } from './components/employee/users-list/users-list.component';
import { InvoicesListComponent } from './components/employee/invoices-list/invoices-list.component';
import { BusinessReportsComponent } from './components/employee/business-reports/business-reports.component';
import {NgxPaginationModule} from "ngx-pagination";
import { UserElementComponent } from './components/employee/users-list/user-element/user-element.component';
import { ElementInvoicesComponent } from './components/employee/users-list/user-element/element-invoices/element-invoices.component';
import { ElementPersonalDataComponent } from './components/employee/users-list/user-element/element-personal-data/element-personal-data.component';
import { EmployeeElementComponent } from './components/employee/employee-edit/employee-element/employee-element.component';
import { ElementPersonalDataFormComponent } from './components/employee/employee-edit/employee-element/element-personal-data-form/element-personal-data-form.component';
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
    ActivePassesComponent,
    UserInvoicesComponent,
    ManagementComponent,
    LiftManagementComponent,
    SchedulesComponent,
    PricelistEditComponent,
    EmployeeAddComponent,
    EmployeeEditComponent,
    UsersListComponent,
    InvoicesListComponent,
    BusinessReportsComponent,
    UserElementComponent,
    ElementInvoicesComponent,
    ElementPersonalDataComponent,
    EmployeeElementComponent,
    ElementPersonalDataFormComponent
  ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        AppRoutingModule,
        NgxPaginationModule
    ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
