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


const routes: Routes = [
  {path: 'contact', component: ContactComponent},
  {path: 'pricelist', component: PricelistComponent},
  {
    path: 'profile', component: ProfileComponent, canActivate: [RouteGuard],
    data: {expectedRole: 'ROLE_USER'},
    children: [
      {path: 'purchase', component: PurchaseComponent}
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
