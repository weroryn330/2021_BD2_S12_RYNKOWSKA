import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../../services/employee.service";

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {
  employeesList: any;
  page = 1;

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit(): void {
    this.getEmployeesList();
  }

  private getEmployeesList() {
    this.employeeService.getEmployees().subscribe((data: any) => {
        this.employeesList = data;
        console.log(data);
      },
      error => {
        alert("Nie znaleziono pracowników");
        this.employeesList = [];
      })
  }

  updateEmployee(newEmployee: any, index: number) {
    if (index === newEmployee[1])
      this.employeesList[index] = newEmployee[0];
  }
}
