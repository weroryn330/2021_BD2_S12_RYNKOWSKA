import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {TokenService} from "../../services/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../registration/registration.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    email: null,
    password: null
  };

  constructor(private router: Router, private loginService: LoginService, private token: TokenService) {
  }

  ngOnInit(): void {
  }

  goToRegistration() {
    this.router.navigateByUrl('/register');
  }


  onSubmit() {
    const {email, password} = this.form;
    this.loginService.login(email, password).subscribe(data => {
        this.token.saveToken(data.token);
        this.token.saveUser(data);
        alert("Zalogowano pomyślnie!");
        this.router.navigateByUrl('/welcome');
      },
      error => {
        console.log(error);
        alert("Logowanie niepomyślne!" + error.error.message);
      })
  }
}
