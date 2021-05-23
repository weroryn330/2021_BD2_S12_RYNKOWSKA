import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {WelcomePageComponent} from "./components/welcome-page/welcome-page.component";
import {LoginComponent} from "./components/login/login.component";
import {LiftsComponent} from "./components/lifts/lifts.component";
import {ContactComponent} from "./components/contact/contact.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {RouteGuard} from "./services/route.guard";


const routes: Routes = [
  {path: 'contact', component: ContactComponent},
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
