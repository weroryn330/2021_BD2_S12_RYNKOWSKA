import {Component, OnInit} from '@angular/core';
import {RegistrationService} from "../../services/registration.service";
import {Router} from "@angular/router";

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
  isFormSend = false;

  constructor(private registationService: RegistrationService, private router: Router) {
  }

  ngOnInit(): void {
    this.isFormSend = false;
  }

  validatePostalCode(): void {
    if (this.form.postalCode == null || this.form.postalCode.length != 6 || this.form.postalCode.charAt(2) != '-') {
      alert("Niepoprawny format kodu pocztowego!\nWpisz kod w postaci: [][]-[][][], np: 44-100")
    }
    if (this.form.postalCode == null || isNaN(this.form.postalCode.charAt(0)) || isNaN(this.form.postalCode.charAt(1)) ||
      isNaN(this.form.postalCode.charAt(3)) || isNaN(this.form.postalCode.charAt(4)) || isNaN(this.form.postalCode.charAt(5))) {
      alert("Niepoprawny format kodu pocztowego!\nPrawidłowy format musi składać się z cyfr, np: 44-100")
    }
  }

  insertDash(): void {
    if (this.form.postalCode.length == 2) {
      this.form.postalCode = this.form.postalCode + '-'
    }
  }

  validatePhone(): void {
    if (this.form.phone == null || this.form.phone.length != 9) {
      alert("Niepoprawny format numeru telefonu!\nPrawidłowy format musi składać się z 9 cyfr, np: 600700800")
      return;
    }
    for(let i=0; i<this.form.phone.length; i++) {
      if (isNaN(this.form.phone.charAt(i))){
        alert("Niepoprawny format numeru telefonu!\nPrawidłowy format musi składać się z cyfr, np: 600700800")
      }
    }
  }

  onSubmit(): void {
    const { firstName, lastName, country, email, phone, voivodeship, postalCode, city, address, password } = this.form;
    this.registationService.register(firstName, lastName, country, email,
      phone, voivodeship, postalCode, city, address, password).subscribe( data => {
        console.log(data);
        this.isRegistrationSuccessful = true;
        this.isFormSend = true;
        alert("Rejestracja przebiegła pomyślnie!");

    },
      error => {
        this.isRegistrationSuccessful = false;
        this.isFormSend = true;
        alert("Użytkownik o podanym adresie email już istnieje!");
      })
  }
}
