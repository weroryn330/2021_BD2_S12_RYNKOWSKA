import {Component, Input, OnInit} from '@angular/core';
import {EmployeeService} from "../../../../../services/employee.service";
import {RegistrationRequest} from "../../../../../classes/registration-request";

@Component({
  selector: 'app-element-password-form',
  templateUrl: './element-password-form.component.html',
  styleUrls: ['./element-password-form.component.css']
})
export class ElementPasswordFormComponent implements OnInit {
  @Input() employee: any;
  form: any = {
    password: null,
    passwordRepetition: null
  }

  constructor(private employeeService: EmployeeService) { }
  ngOnInit(): void {
  }

  onSubmit() {
    const registrationRequest = new RegistrationRequest(this.employee.firstName, this.employee.lastName,
      this.employee.country, this.employee.email, this.employee.phone, this.employee.voivodeship,
      this.employee.postalCode, this.employee.city, this.employee.address, this.form.password);
      this.employeeService.updateEmployeePassword(registrationRequest).subscribe(data => {
        alert("Zmiana hasła przebiegła pomyślnie");
      },
      error => {
        console.log(error.message);
        alert("Hasło nie może być takie samo");
      })
  }
}
