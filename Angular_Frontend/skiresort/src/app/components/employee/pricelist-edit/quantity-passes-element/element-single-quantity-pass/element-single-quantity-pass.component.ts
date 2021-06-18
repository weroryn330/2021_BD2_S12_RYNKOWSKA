import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-element-single-quantity-pass',
  templateUrl: './element-single-quantity-pass.component.html',
  styleUrls: ['./element-single-quantity-pass.component.css']
})
export class ElementSingleQuantityPassComponent implements OnInit {
  @Input() quantityPass: any;
  @Input() index: any;
  @Input() passesApproved: any;
  @Output() newQuantityPassModificationEvent = new EventEmitter<[any,any]>();
  @Output() newQuantityPassDeletionEvent = new EventEmitter<[any,any]>();
  form: any = {
    quantity: null,
    price: null
  };
  isPassConfirmed = false;
  constructor() { }

  ngOnInit(): void {
    this.form.quantity = this.quantityPass.quantity;
    this.form.price = this.quantityPass.price;
  }

  editQuantityPass() {
    this.newQuantityPassModificationEvent.emit([this.quantityPass , this.index]);
    this.isPassConfirmed = true;
  }

  deleteQuantityPass() {
    this.newQuantityPassDeletionEvent.emit([this.quantityPass , this.index]);
  }
}
