import {Component, OnInit} from '@angular/core';
import {RegistrationRequest} from "../../classes/registration-request";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {TokenService} from "../../services/token.service";

@Component({
  selector: 'app-user-edit-info',
  templateUrl: './user-edit-info.component.html',
  styleUrls: ['./user-edit-info.component.css',
    '../registration/registration.component.css']
})
export class UserEditInfoComponent implements OnInit {
  form: any = {
    firstName: null,
    lastName: null,
    phone: null,
    country: null,
    voivodeship: null,
    postalCode: null,
    city: null,
    address: null
  }
  userData: any;


  constructor(private userService: UserService, private router: Router, private token: TokenService) {
  }


  ngOnInit(): void {
    this.userData = this.token.getUser();
    this.fillData();
  }

  insertDash(): void {
    if (this.form.postalCode) {
      if (this.form.postalCode.length === 2) {
        this.form.postalCode = this.form.postalCode + '-'
      }
    }
  }

  fillData() {
    this.form.firstName = this.userData.firstName;
    this.form.lastName = this.userData.lastName;
    this.form.phone = this.userData.phone;
    this.form.country = this.userData.country;
    this.form.voivodeship = this.userData.voivodeship;
    this.form.postalCode = this.userData.postalCode;
    this.form.city = this.userData.city;
    this.form.address = this.userData.address;
  }

  onSubmit(): void {
    const {
      firstName,
      lastName,
      phone,
      country,
      voivodeship,
      postalCode,
      city,
      address
    } = this.form;
    const newUserInfo = new RegistrationRequest(firstName, lastName,
      country, this.token.getUser().email, phone, voivodeship, postalCode, city, address, '');
    this.userService.changeUserInfo(newUserInfo).subscribe(data => {
        alert("Zmiana danych pomyślnie!");
        this.token.saveUser(data);
        this.router.navigateByUrl('/profile');

      },
      error => {
        alert("Coś poszło nie tak...");
      })
  }

}
