import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-element-single-time-pass',
  templateUrl: './element-single-time-pass.component.html',
  styleUrls: ['./element-single-time-pass.component.css']
})
export class ElementSingleTimePassComponent implements OnInit {
  @Input() timePass: any;
  @Input() index: any;
  @Input() passesApproved: any;
  @Output() newTimePassModificationEvent = new EventEmitter<[any, any]>();
  @Output() newTimePassDeletionEvent = new EventEmitter<[any, any]>();
  form: any = {
    hours: null,
    price: null
  };
  isPassConfirmed = false;

  constructor() {
  }

  ngOnInit(): void {
    this.form.hours = this.timePass.hours;
    this.form.price = this.timePass.price;
  }


  editTimePass() {
    this.newTimePassModificationEvent.emit([this.timePass, this.index]);
    this.isPassConfirmed = true;
  }

  deleteTimePass() {
    this.newTimePassDeletionEvent.emit([this.timePass, this.index]);
  }
}
