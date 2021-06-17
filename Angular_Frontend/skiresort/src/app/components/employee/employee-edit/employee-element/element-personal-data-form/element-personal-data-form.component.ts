import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EmployeeService} from "../../../../../services/employee.service";
import {RegistrationRequest} from "../../../../../classes/registration-request";

@Component({
  selector: 'app-element-personal-data-form',
  templateUrl: './element-personal-data-form.component.html',
  styleUrls: ['./element-personal-data-form.component.css']
})
export class ElementPersonalDataFormComponent implements OnInit {
  @Input() employee: any;
  @Output() newEmployeeChangeEvent = new EventEmitter<any>();
  form: any = {
    firstName: null,
    lastName: null,
    country: null,
    phone: null,
    voivodeship: null,
    postalCode: null,
    city: null,
    address: null
  }

  constructor(private employeeService: EmployeeService) {
  }

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
  }

  onSubmit() {
    const {
      firstName,
      lastName,
      country,
      voivodeship,
      postalCode,
      city,
      address,
      phone
    } = this.form;
    const registrationRequest = new RegistrationRequest(firstName, lastName, country, this.employee.email,
      phone, voivodeship, postalCode, city, address, '');
    this.employeeService.updateEmployeePersonalData(registrationRequest).subscribe(data => {
        alert("Zmiana danych osobowych przebiegła pomyślnie");
        this.newEmployeeChangeEvent.emit(data);
      },
      error => {
        console.log(error.message);
        alert("Użytkownik o podanym adresie email już istnieje!");
      })
  }
}
