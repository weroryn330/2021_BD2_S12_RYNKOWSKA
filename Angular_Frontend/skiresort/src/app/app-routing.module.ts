import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {WelcomePageComponent} from "./components/welcome-page/welcome-page.component";
import {LoginComponent} from "./components/login/login.component";
import {LiftsComponent} from "./components/lifts/lifts.component";
import {ContactComponent} from "./components/contact/contact.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {RouteGuard} from "./services/route.guard";
import {PricelistComponent} from "./components/pricelist/pricelist.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {PurchaseComponent} from "./components/purchase/purchase.component";
import {UserPassesComponent} from "./components/user-passes/user-passes.component";
import {UserReportComponent} from "./components/user-report/user-report.component";
import {UserInfoComponent} from "./components/user-info/user-info.component";
import {UserEditInfoComponent} from "./components/user-edit-info/user-edit-info.component";
import {UserEditCredentialsComponent} from "./components/user-edit-credentials/user-edit-credentials.component";
import {ActivePassesComponent} from "./components/employee/active-passes/active-passes.component";
import {UserInvoicesComponent} from "./components/user-invoices/user-invoices.component";
import {ManagementComponent} from "./components/management/management.component";
import {LiftManagementComponent} from "./components/lift-management/lift-management.component";
import {SchedulesComponent} from "./components/employee/schedules/schedules.component";
import {PricelistEditComponent} from "./components/employee/pricelist-edit/pricelist-edit.component";
import {EmployeeAddComponent} from "./components/employee/employee-add/employee-add.component";
import {EmployeeEditComponent} from "./components/employee/employee-edit/employee-edit.component";
import {UsersListComponent} from "./components/employee/users-list/users-list.component";
import {InvoicesListComponent} from "./components/employee/invoices-list/invoices-list.component";
import {BusinessReportsComponent} from "./components/employee/business-reports/business-reports.component";


const routes: Routes = [
  {path: 'contact', component: ContactComponent},
  {
    path: 'management', component: ManagementComponent, canActivate: [RouteGuard],
    data: {expectedRole: 'ROLE_EMPLOYEE'},
    children: [
      {path: 'active-passes', component: ActivePassesComponent},
      {path: 'schedules', component: SchedulesComponent},
      {path: 'pricelists', component: PricelistEditComponent},
      {path: 'add-employee', component: EmployeeAddComponent, canActivate: [RouteGuard],
      data: {expectedRole: 'ROLE_OWNER'}},
      {path: 'edit-employee', component: EmployeeEditComponent, canActivate: [RouteGuard],
        data: {expectedRole: 'ROLE_OWNER'}},
      {path: 'users', component: UsersListComponent, canActivate: [RouteGuard],
        data: {expectedRole: 'ROLE_OWNER'}},
      {path: 'invoices', component: InvoicesListComponent, canActivate: [RouteGuard],
        data: {expectedRole: 'ROLE_OWNER'}},
      {path: 'reports', component: BusinessReportsComponent, canActivate: [RouteGuard],
        data: {expectedRole: 'ROLE_OWNER'}},
      {path: '', redirectTo: 'active-passes', pathMatch: 'full'}
    ]
  },
  {path: 'skilift-management', component: LiftManagementComponent, canActivate: [RouteGuard],
    data: {expectedRole: 'ROLE_TECHNICAL_EMPLOYEE'}},
  {path: 'pricelist', component: PricelistComponent},
  {
    path: 'profile', component: ProfileComponent, canActivate: [RouteGuard],
    data: {expectedRole: 'ROLE_USER'},
    children: [
      {path: 'passes', component: UserPassesComponent},
      {path: 'invoices', component: UserInvoicesComponent},
      {path: 'purchase', component: PurchaseComponent},
      {path: 'report', component: UserReportComponent},
      {path: 'info', component: UserInfoComponent},
      {path: 'edit-info', component: UserEditInfoComponent},
      {path: 'edit-credentials', component: UserEditCredentialsComponent},
      {path: '', redirectTo: 'passes', pathMatch: 'full'}
    ]
  },
  {path: 'lifts', component: LiftsComponent},
  {
    path: 'login', component: LoginComponent, canActivate: [RouteGuard],
    data: {expectedRole: 'ROLE_NONE'}
  },
  {
    path: 'register', component: RegistrationComponent, canActivate: [RouteGuard],
    data: {expectedRole: 'ROLE_NONE'}
  },
  {path: 'welcome', component: WelcomePageComponent},
  {path: '', redirectTo: '/welcome', pathMatch: 'full'},
  {path: '**', redirectTo: '/welcome', pathMatch: 'full'}
]


@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
