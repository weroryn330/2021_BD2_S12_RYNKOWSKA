import {Component, Input, OnInit} from '@angular/core';


@Component({
  selector: 'app-employee-element',
  templateUrl: './employee-element.component.html',
  styleUrls: ['./employee-element.component.css']
})
export class EmployeeElementComponent implements OnInit {
  @Input() employee: any;
  isFormVisible = false;
  constructor() { }

  ngOnInit(): void {
  }


  showEditForm() {
    this.isFormVisible = ! this.isFormVisible;
  }
}
