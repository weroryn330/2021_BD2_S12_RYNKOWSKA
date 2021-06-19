import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';


@Component({
  selector: 'app-employee-element',
  templateUrl: './employee-element.component.html',
  styleUrls: ['./employee-element.component.css']
})
export class EmployeeElementComponent implements OnInit {
  @Input() employee: any;
  @Input() index: any;
  @Output() newUpdateEmployeeEvent = new EventEmitter<[any,any]>();
  isFormVisible = false;
  role: any;
  constructor() { }

  ngOnInit(): void {
    if (this.employee.roleList.includes('ROLE_OWNER'))
      this.role = 'Właściciel';
    else if(this.employee.roleList.includes('ROLE_EMPLOYEE'))
      this.role = "Pracownik zwykły";
    else
      this.role = "Pracownik techniczny";
  }


  showEditForm() {
    this.isFormVisible = ! this.isFormVisible;
  }

  updateEmployee(newEmployee: any) {
    this.employee = newEmployee;
    this.ngOnInit();
    this.newUpdateEmployeeEvent.emit([this.employee, this.index]);
  }
}
