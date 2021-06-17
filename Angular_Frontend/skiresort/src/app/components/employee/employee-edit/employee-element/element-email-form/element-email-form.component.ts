import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EmployeeService} from "../../../../../services/employee.service";
import {RegistrationRequest} from "../../../../../classes/registration-request";

@Component({
  selector: 'app-element-email-form',
  templateUrl: './element-email-form.component.html',
  styleUrls: ['./element-email-form.component.css']
})
export class ElementEmailFormComponent implements OnInit {
  @Input() employee: any;
  @Output() newEmployeeChangeEvent = new EventEmitter<any>();
  form: any = {
    email: null,
    emailRepetition: null
  }

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const registrationRequest = new RegistrationRequest(this.employee.firstName, this.employee.lastName,
      this.employee.country, this.employee.email, this.employee.phone, this.employee.voivodeship,
      this.employee.postalCode, this.employee.city, this.employee.address, '');

    this.employeeService.updateEmployeeEmail(registrationRequest, this.form.email).subscribe(data => {
        alert("Zmiana adresu email przebiegła pomyślnie");
        this.newEmployeeChangeEvent.emit(data);
      },
      error => {
        console.log(error.message);
        alert("Użytkownik o podanym adresie email już istnieje!");
      })
  }
}
