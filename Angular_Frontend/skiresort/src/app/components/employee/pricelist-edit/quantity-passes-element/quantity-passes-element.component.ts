import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {QuantityPassResponse} from "../../../../classes/quantity-pass-response";

@Component({
  selector: 'app-quantity-passes-element',
  templateUrl: './quantity-passes-element.component.html',
  styleUrls: ['./quantity-passes-element.component.css']
})
export class QuantityPassesElementComponent implements OnInit {
  @Input() quantityPasses: any;
  @Output() newQuantityPassesChangeEvent = new EventEmitter<any>();

  validationArray: Boolean[] = [];
  areQuantityPassesValid = false;
  isButtonClicked = false;
  constructor() {
  }

  ngOnInit(): void {
    for (let i = 0; i < this.quantityPasses.length; i++) {
      this.validationArray[i] = false;
    }
  }

  deleteQuantityPass(quantityPassAndIndexTuple: [any, any]) {
    this.quantityPasses.splice(quantityPassAndIndexTuple[1], 1);
    this.validationArray.splice(quantityPassAndIndexTuple[1], 1);
  }

  modifyQuantityPass(quantityPassAndIndexTuple: [any, any]) {

    this.quantityPasses[quantityPassAndIndexTuple[1]] = quantityPassAndIndexTuple[0];
    this.validationArray[quantityPassAndIndexTuple[1]] = true;
  }

  addNewPass() {
    this.quantityPasses.push(new QuantityPassResponse(0,0));
    this.validationArray.push(false);
  }

  checkIfPassesAreValid() {
    for (const passFlag of this.validationArray) {
      if (!passFlag)
        return false;
    }
    return true;
  }

  acceptQuantityPasses() {
    this.isButtonClicked = true;
    if (this.checkIfPassesAreValid()) {
      this.areQuantityPassesValid = true;
      this.newQuantityPassesChangeEvent.emit(this.quantityPasses);
      return;
    }
    this.areQuantityPassesValid = false;
  }
}
