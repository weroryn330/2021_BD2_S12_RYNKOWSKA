import {Component, Input, OnInit} from '@angular/core';
import {UserResponse} from "../../../../classes/user-response";

@Component({
  selector: 'app-user-element',
  templateUrl: './user-element.component.html',
  styleUrls: ['./user-element.component.css']
})
export class UserElementComponent implements OnInit {
  @Input() user: any;
  areInvoicesVisible = false;
  arePersonalDataVisible = false;
  constructor() { }

  ngOnInit(): void {
  }

  showInvoices() {
    this.areInvoicesVisible = true;
    this.arePersonalDataVisible = false;
  }

  showPersonalData() {
    this.areInvoicesVisible = false;
    this.arePersonalDataVisible = true;
  }

  closeComponent(option: number) {
    if(option)
      this.arePersonalDataVisible = false;
    else
      this.areInvoicesVisible = false;
  }
}
