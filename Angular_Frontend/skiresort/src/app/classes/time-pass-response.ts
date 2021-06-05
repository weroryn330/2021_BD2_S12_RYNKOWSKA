import {PricelistPass} from "./pricelist-pass";

export class TimePassResponse implements PricelistPass{
  hours: number;
  price: number;

  constructor(hours: number, price: number) {
    this.hours = hours;
    this.price = price;
  }
}
