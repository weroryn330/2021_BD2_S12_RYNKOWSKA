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


const routes: Routes = [
  {path: 'contact', component: ContactComponent},
  {
    path: 'employee/active-passes', component: ActivePassesComponent, canActivate: [RouteGuard],
    data: {expectedRole: 'ROLE_EMPLOYEE'}
  },
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
