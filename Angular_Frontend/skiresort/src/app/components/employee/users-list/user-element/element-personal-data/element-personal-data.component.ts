import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PassRequest} from "../../../../../classes/pass-request";

@Component({
  selector: 'app-element-personal-data',
  templateUrl: './element-personal-data.component.html',
  styleUrls: ['./element-personal-data.component.css',]
})
export class ElementPersonalDataComponent implements OnInit {

  @Input() user: any;
  @Output() newCloseComponentEvent = new EventEmitter<number>();

  constructor() {
  }

  ngOnInit(): void {
  }

  closePersonalData() {
    this.newCloseComponentEvent.emit(1);
  }


}
