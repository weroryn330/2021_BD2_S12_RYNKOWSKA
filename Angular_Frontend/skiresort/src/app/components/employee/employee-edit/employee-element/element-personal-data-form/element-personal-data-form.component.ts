import {Component, Input, OnInit} from '@angular/core';
import {EmployeeService} from "../../../../../services/employee.service";
import {RegistrationRequest} from "../../../../../classes/registration-request";

@Component({
  selector: 'app-element-personal-data-form',
  templateUrl: './element-personal-data-form.component.html',
  styleUrls: ['./element-personal-data-form.component.css']
})
export class ElementPersonalDataFormComponent implements OnInit {
  @Input() employee: any;
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
    passwordRepetition: null,
    employeeType: "ROLE_TECHNICIAN"
  }

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
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
    this.form.firstName = this.employee.firstName;
    this.form.lastName = this.employee.lastName;
    this.form.phone = this.employee.phone;
    this.form.country = this.employee.country;
    this.form.voivodeship = this.employee.voivodeship;
    this.form.postalCode = this.employee.postalCode;
    this.form.city = this.employee.city;
    this.form.address = this.employee.address;
    this.form.email = this.employee.email;
    this.form.emailRepetition = this.employee.email;
  }

  onSubmit() {
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
    this.employeeService.updateEmployee(registrationRequest, this.form.employeeType, this.employee.id).subscribe(data => {
        alert("Rejestracja przebiegła pomyślnie!");
      },
      error => {
        alert("Użytkownik o podanym adresie email już istnieje!");
      })
  }
}
