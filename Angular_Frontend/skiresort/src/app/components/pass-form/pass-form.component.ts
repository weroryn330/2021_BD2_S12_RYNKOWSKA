import {Component, Input, OnInit, Output} from '@angular/core';
import {PassRequest} from "../../classes/pass-request";
import {EventEmitter} from "@angular/core";
import {TimePassResponse} from "../../classes/time-pass-response";
import {PricelistPass} from "../../classes/pricelist-pass";

@Component({
  selector: 'app-pass-form',
  templateUrl: './pass-form.component.html',
  styleUrls: ['./pass-form.component.css',
    '../registration/registration.component.css']
})
export class PassFormComponent implements OnInit {
  @Input() pricelist: any;
  @Input() pass: any;
  @Output() newRequestEvent = new EventEmitter<PassRequest>();
  @Output() newValidateEvent = new EventEmitter<boolean>();

  form: any = {
    firstName: null,
    lastName: null,
    birthDate: null,
    usesTotal: null,
    startDate: null,
    passType: null,
    passTime: null,
    unitTotal: null
  }
  activeDiscountPercentage: number = 0;
  unitPrice: any;
  passTypes = ['Karnet czasowy', 'Karnet zjazdowy'];
  isSubmitted = false;

  constructor() {
  }

  ngOnInit(): void {
    if (this.pass) {
      if (this.pass.hours) {
        this.form.passType = this.passTypes[0];
        this.form.passTime = this.pass.hours;
        this.calculatePrice(this.form.passTime);
      } else {
        this.form.passType = this.passTypes[1];
        this.form.usesTotal = this.pass.quantity;
        this.calculatePrice(this.form.usesTotal);
      }
    }
  }

  checkIfDiscountApply(birthDate: Date) {
    this.activeDiscountPercentage = 0;
    const age = (Date.now() - new Date(birthDate).getTime()) / 31536000000;
    for(const discount of this.pricelist.ageDiscountsList) {
      if (age > discount.ageMin && age < discount.ageMax) {
        this.activeDiscountPercentage = discount.percentage;
        break;
      }
    }
    if (this.form.passType === this.passTypes[0]) {
      this.calculatePrice(this.form.passTime);
    } else {
      this.calculatePrice(this.form.usesTotal);
    }
  }

  addNewRequest(request: PassRequest) {
    this.newRequestEvent.emit(request);
  }

  passFormValidation(validate: boolean) {
    this.newValidateEvent.emit(validate);
  }

  onSubmit() {
    if (this.form.passType == 'Karnet czasowy') {
      this.form.usesTotal = null;
    } else {
      this.form.passTime = null;
      this.form.startDate = null;
    }
    const endDate = new Date();
    endDate.setTime(new Date(this.form.startDate).getTime() + this.form.passTime*60*60*1000);
    const passRequest = new PassRequest(this.form.unitPrice,this.form.firstName, this.form.lastName,
      this.form.startDate, endDate, this.form.birthDate, this.form.usesTotal);
     this.isSubmitted = true;
    this.addNewRequest(passRequest);
    this.passFormValidation(true);
  }


  calculatePrice(value: number) {
    let standardPrice: number;
    if (this.form.passType == 'Karnet czasowy') {
      let pass = this.pricelist.timePassesList.find((i: any) => i.hours == value);
      if (!pass) {
        return;
      }
      standardPrice = pass.price;
    } else {
      let pass = this.pricelist.quantityPassesList.find((i: any) => i.quantity == value);
      if (!pass) {
        return;
      }
      standardPrice = pass.price;
    }
      this.form.unitPrice = parseInt((standardPrice * (100 - this.activeDiscountPercentage) / 100).toPrecision(3));
  }

  convertToDays(hours: number): string {
    if (hours < 24) {
      return hours + 'H';
    } else if (hours == 24) {
      return 1 + ' DZIEŃ';
    } else {
      return parseInt((hours / 24).toPrecision(2)) + ' DNI';
    }
  }

}
