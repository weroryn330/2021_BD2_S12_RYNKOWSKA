import {Component, OnInit} from '@angular/core';
import {RegistrationService} from "../../services/registration.service";
import {Router} from "@angular/router";
import {RegistrationRequest} from "../../classes/registration-request";

@Component({
  selector: 'app-register',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  form: any = {
    firstName: null,
    lastName: null,
    country: null,
    email: null,
    emailRepetition: null,
    phone: null,
    voivodeship: null,
    postalCode: null,
    city: null,
    address: null,
    password: null,
    passwordRepetition: null
  }

  constructor(private registationService: RegistrationService, private router: Router) {
  }

  ngOnInit(): void {
  }

  insertDash(): void {
    if (this.form.postalCode) {
      if (this.form.postalCode.length === 2) {
        this.form.postalCode = this.form.postalCode + '-'
      }
    }
  }

  onSubmit(): void {
    const {
      firstName,
      lastName,
      country,
      email,
      phone,
      voivodeship,
      postalCode,
      city,
      address,
      password
    } = this.form;
    const registrationRequest = new RegistrationRequest(firstName, lastName, country, email, phone, voivodeship, postalCode, city, address, password);
    this.registationService.register(registrationRequest).subscribe(data => {
        alert("Rejestracja przebiegła pomyślnie!");
        this.router.navigateByUrl('/login');

      },
      error => {
        alert("Użytkownik o podanym adresie email już istnieje!");
      })
  }
}
