import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {QuantityPassResponse} from "../../../../classes/quantity-pass-response";
import {TimePassResponse} from "../../../../classes/time-pass-response";

@Component({
  selector: 'app-time-passes-element',
  templateUrl: './time-passes-element.component.html',
  styleUrls: ['./time-passes-element.component.css']
})
export class TimePassesElementComponent implements OnInit {
  @Input() timePasses: any;
  @Output() newTimePassesChangeEvent = new EventEmitter<any>();
  validationArray: Boolean[] = [];
  areTimePassesValid = false;
  isButtonClicked = false;
  constructor() {
  }

  ngOnInit(): void {
    for (let i = 0; i < this.timePasses.length; i++) {
      this.validationArray[i] = false;
    }
  }

  deleteTimePass(timePassAndIndexTuple: [any, any]) {
    this.timePasses.splice(timePassAndIndexTuple[1], 1);
    this.validationArray.splice(timePassAndIndexTuple[1], 1);
    console.log('PASS DELETED: ' + timePassAndIndexTuple[0]);
  }

  modifyTimePass(timePassAndIndexTuple: [any, any]) {
    this.timePasses[timePassAndIndexTuple[1]] = timePassAndIndexTuple[0];
    this.validationArray[timePassAndIndexTuple[1]] = true;
    console.log('PASS CHANGED: ' + timePassAndIndexTuple[0]);
  }

  addNewPass() {
    this.timePasses.push(new TimePassResponse(0,0));
    this.validationArray.push(false);
  }

  checkIfPassesAreValid() {
    for (const passFlag of this.validationArray) {
      if (!passFlag)
        return false;
    }
    return true;
  }

  acceptTimePasses() {
    this.isButtonClicked = true;
    if (this.checkIfPassesAreValid()) {
      this.areTimePassesValid = true;
      this.newTimePassesChangeEvent.emit(this.timePasses);
      return;
    }
    this.areTimePassesValid = false;
  }
}
