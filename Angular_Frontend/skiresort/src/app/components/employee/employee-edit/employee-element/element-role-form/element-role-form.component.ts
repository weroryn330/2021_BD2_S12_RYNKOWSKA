import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EmployeeService} from "../../../../../services/employee.service";
import {RegistrationRequest} from "../../../../../classes/registration-request";

@Component({
  selector: 'app-element-role-form',
  templateUrl: './element-role-form.component.html',
  styleUrls: ['./element-role-form.component.css']
})
export class ElementRoleFormComponent implements OnInit {
  @Input() employee: any;
  @Output() newEmployeeChangeEvent = new EventEmitter<any>();
  form: any = {
    employeeType: "ROLE_TECHNICIAN"
  }

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.employeeService.updateEmployeeRole(this.employee.email, this.form.employeeType).subscribe(data => {
        alert("Zmiana roli przebiegła pomyślnie");
        this.newEmployeeChangeEvent.emit(data);
      },
      error => {
        console.log(error.message);
        alert("Nie można zmienić roli!");
      })
  }
}
