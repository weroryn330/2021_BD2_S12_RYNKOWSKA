import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../services/token.service";
import {UserService} from "../../services/user.service";
import {RegistrationRequest} from "../../classes/registration-request";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-edit-credentials',
  templateUrl: './user-edit-credentials.component.html',
  styleUrls: ['./user-edit-credentials.component.css',
    '../registration/registration.component.css']
})
export class UserEditCredentialsComponent implements OnInit {
  emailForm: any = {
    email: null,
    emailRepetition: null
  }

  passwordForm: any = {
    password: null,
    passwordRepetition: null
  }
  constructor(private token: TokenService, private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
  }

  onSubmitPassword() {
    this.userService.changePassword(new RegistrationRequest(this.token.getUser().firstName,
      this.token.getUser().lastName,this.token.getUser().country, this.token.getUser().email,
      this.token.getUser().phone, this.token.getUser().voivodeship, this.token.getUser().postalCode,
      this.token.getUser().city, this.token.getUser().address, this.passwordForm.password)).subscribe(data => {
        this.token.saveToken(data.token);
        this.token.saveUser(data);
        this.router.navigateByUrl('/profile');
        alert("Hasło zmieniono pomyślnie!");
      },
      error => {

        alert("Hasło zmieniono niepomyślne!" + error.error.message);
      })
  }

  onSubmitEmail() {
    this.userService.changeEmail(new RegistrationRequest(this.token.getUser().firstName,
      this.token.getUser().lastName,this.token.getUser().country, this.emailForm.email,
      this.token.getUser().phone, this.token.getUser().voivodeship, this.token.getUser().postalCode,
      this.token.getUser().city, this.token.getUser().address, '')).subscribe(data => {
        this.token.saveToken(data.token);
        this.token.saveUser(data);
        this.router.navigateByUrl('/profile');
        alert("Email zmieniono pomyślnie!");
      },
      error => {

        alert("Email zmieniono niepomyślne!" + error.error.message);
      })
  }
}
