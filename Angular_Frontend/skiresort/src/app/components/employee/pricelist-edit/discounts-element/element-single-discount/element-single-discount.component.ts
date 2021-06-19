import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-element-single-discount',
  templateUrl: './element-single-discount.component.html',
  styleUrls: ['./element-single-discount.component.css']
})
export class ElementSingleDiscountComponent implements OnInit {
  @Input() discount: any ;
  @Input() index: any;
  @Input() discountsApproved: any;
  @Output() newDiscountModification = new EventEmitter<[any,any]>();
  @Output() newInvalidInput = new EventEmitter<any>();
  form: any = {
    percentage: null
  };
  isSubmitted = false;
  constructor() { }

  ngOnInit(): void {
    this.form.percentage = this.discount.percentage;
  }

  onSubmit() {
    this.newDiscountModification.emit([this.form.percentage , this.index]);
    this.isSubmitted = true;
  }

  onSubmitInvalidData() {
    this.newInvalidInput.emit( this.index);
    this.isSubmitted = false;
    return false;
  }
}
