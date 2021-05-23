import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {WelcomePageComponent} from "./components/welcome-page/welcome-page.component";
import {LoginComponent} from "./components/login/login.component";
import {LiftsComponent} from "./components/lifts/lifts.component";
import {ContactComponent} from "./components/contact/contact.component";
import {RegistrationComponent} from "./components/registration/registration.component";


const routes: Routes = [
  {path: 'contact', component: ContactComponent},
  {path: 'lifts', component: LiftsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
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
