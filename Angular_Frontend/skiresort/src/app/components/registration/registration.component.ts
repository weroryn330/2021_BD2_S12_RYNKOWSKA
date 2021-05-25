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
  isRegistrationSuccessful = false;
  isFormValid = false;
  isFormSend = false;

  constructor(private registationService: RegistrationService, private router: Router) {
  }

  ngOnInit(): void {
    this.isFormSend = false;
  }

  insertDash(): void {
    if (this.form.postalCode.length == 2) {
      this.form.postalCode = this.form.postalCode + '-'
    }
  }

  checkIfNotEmpty(element: HTMLInputElement): boolean {
    if (element.value == null || (element.value != null && element.value.trim().length === 0)) {
      return false;
    }
    return true;
  }

  changeElementColors(element: HTMLInputElement, backgroundColor: string, color: string) {
    element!!.style.backgroundColor = backgroundColor;
    element!!.style.color = color;
  }

  validatePostalCode(id: string): void {
    const element = <HTMLInputElement>window.document.getElementById(id);
    if (!element.value.match('^[0-9]{2}-[0-9]{3}$')) {
      this.changeElementColors(element, 'rgba(250,0,0,0.3)', 'white');
      this.isFormValid = false;
      return;
    }
    this.changeElementColors(element, 'white', 'black');
    this.isFormValid = true;
  }


  validatePhone(id: string): void {
    const element = <HTMLInputElement>window.document.getElementById(id);
    if (!element.value.match('^[0-9]{9}$')) {
      this.changeElementColors(element, 'rgba(250,0,0,0.3)', 'white');
      this.isFormValid = false;
      return;
    }
    this.changeElementColors(element, 'white', 'black');
    this.isFormValid = true;
  }


  validateSimpleInput(id: string) {
    const element = <HTMLInputElement>window.document.getElementById(id);
    if (!this.checkIfNotEmpty(element) || element.value.length < 3 || element.value.length > 25) {
      this.changeElementColors(element, 'rgba(250,0,0,0.3)', 'white');
      this.isFormValid = false;
      return;
    }
    this.changeElementColors(element, 'white', 'black');
    this.isFormValid = true;
  }

  validateEmail(id: string) {
    const element = <HTMLInputElement>window.document.getElementById(id);
    if (!element.value.match(
      '^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$')) {
      this.changeElementColors(element, 'rgba(250,0,0,0.3)', 'white');
      this.isFormValid = false;
      return;
    }
    this.changeElementColors(element, 'white', 'black');
    this.isFormValid = true;
  }

  validateEmailRepetition(id: string) {
    const element = <HTMLInputElement>window.document.getElementById(id);
    if (this.form.email !== this.form.emailRepetition) {
      this.changeElementColors(element, 'rgba(250,0,0,0.3)', 'white');
      this.isFormValid = false;
      return;
    }
    this.changeElementColors(element, 'white', 'black');
    this.isFormValid = true;
  }

  validatePassword(id: string) {
    const element = <HTMLInputElement>window.document.getElementById(id);
    if (!element.value.match('^[a-zA-Z0-9_-]{5,25}$')) {
      this.changeElementColors(element, 'rgba(250,0,0,0.3)', 'white');
      this.isFormValid = false;
      return;
    }
    this.changeElementColors(element, 'white', 'black');
    this.isFormValid = true;
  }

  validatePasswordRepetition(id: string) {
    const element = <HTMLInputElement>window.document.getElementById(id);
    if (this.form.password !== this.form.passwordRepetition) {
      this.changeElementColors(element, 'rgba(250,0,0,0.3)', 'white');
      this.isFormValid = false;
      return;
    }
    this.changeElementColors(element, 'white', 'black');
    this.isFormValid = true;
  }


  onSubmit(): void {
    const {
      firstName,
      lastName,
      country,
      email,
      emailRepetition,
      phone,
      voivodeship,
      postalCode,
      city,
      address,
      password,
      passwordRepetition
    } = this.form;
    const registrationRequest = new RegistrationRequest(firstName, lastName, country, email, phone, voivodeship, postalCode, city, address, password);
    this.registationService.register(registrationRequest).subscribe(data => {
        this.isRegistrationSuccessful = true;
        this.isFormSend = true;
        alert("Rejestracja przebiegła pomyślnie!");
        this.router.navigateByUrl('/login');

      },
      error => {
        this.isRegistrationSuccessful = false;
        this.isFormSend = true;
        alert("Użytkownik o podanym adresie email już istnieje!");
      })
  }
}
