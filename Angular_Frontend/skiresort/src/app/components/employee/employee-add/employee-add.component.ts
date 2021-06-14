import { Component, OnInit } from '@angular/core';

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
    employeeType: "ROLE_TECHNICAL_EMPLOYEE"
  }

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    return false;
  }

  insertDash() {

  }
}
