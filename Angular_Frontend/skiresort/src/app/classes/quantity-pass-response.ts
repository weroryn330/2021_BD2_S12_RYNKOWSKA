import {PricelistPass} from "./pricelist-pass";

export class QuantityPassResponse implements PricelistPass{

  quantity: number;
  price: number;

  constructor(quantity: number, price: number) {
    this.quantity = quantity;
    this.price = price;
  }
}
