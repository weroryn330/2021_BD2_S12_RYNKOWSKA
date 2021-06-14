import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../../../services/employee.service";
import {RegistrationRequest} from "../../../classes/registration-request";

@Component({
  selector: 'app-employee-add',
  templateUrl: './employee-add.component.html',
  styleUrls: ['./employee-add.component.css',
    '../../registration/registration.component.css']
})
export class EmployeeAddComponent implements OnInit {
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
    this.employeeService.registerEmployee(registrationRequest, this.form.employeeType).subscribe(data => {
        alert("Rejestracja przebiegła pomyślnie!");
      },
      error => {
        alert("Użytkownik o podanym adresie email już istnieje!");
      })
  }

  insertDash() {

  }
}
