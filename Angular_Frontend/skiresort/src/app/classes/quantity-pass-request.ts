export class QuantityPassRequest {
  quantity: number;
  price: number;

  constructor(quantity: number, price: number) {
    this.quantity = quantity;
    this.price = price;
  }
}
