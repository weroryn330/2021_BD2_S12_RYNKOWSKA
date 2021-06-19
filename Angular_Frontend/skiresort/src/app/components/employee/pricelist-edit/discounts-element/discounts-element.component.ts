import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-discounts-element',
  templateUrl: './discounts-element.component.html',
  styleUrls: ['./discounts-element.component.css']
})
export class DiscountsElementComponent implements OnInit {
  @Input() ageDiscounts: any;
  @Output() newDiscountChangeEvent = new EventEmitter<any>();
  validationArray: boolean[] = [];
  areDiscountsValid = false;
  isButtonClicked = false;

  constructor() {
  }

  ngOnInit(): void {
    for (let i = 0; i < this.ageDiscounts.length; i++) {
      this.validationArray[i] = true;
    }
  }

  invalidInputOccured(index: any) {
    this.validationArray[index] = false;
  }

  modifyDiscountsList(discountAndIndex: [any, any]) {
    this.ageDiscounts[discountAndIndex[1]].percentage = discountAndIndex[0];
    this.validationArray[discountAndIndex[1]] = true;
  }

  checkIfDiscountsAreValid() {
    for (const discountFlag of this.validationArray) {
      if (!discountFlag)
        return false;
    }
    return true;
  }

  acceptDiscounts() {
    this.isButtonClicked = true;
    if (this.checkIfDiscountsAreValid()) {
      this.areDiscountsValid = true;
      this.newDiscountChangeEvent.emit(this.ageDiscounts);
      return;
    }
    this.areDiscountsValid = false;
  }
}
