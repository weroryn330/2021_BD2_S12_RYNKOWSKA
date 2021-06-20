
import {AgeDiscountRequest} from "./age-discount-request";
import {QuantityPassRequest} from "./quantity-pass-request";
import {TimePassRequest} from "./time-pass-request";

export class PriceListRequest {
  startDate: string;
  endDate: string;
  ageDiscountsRequest: AgeDiscountRequest[];
  quantityPassRequest: QuantityPassRequest[];
  timePassRequest: TimePassRequest[];


  constructor(startDate: string, endDate: string, ageDiscountsRequest: AgeDiscountRequest[], quantityPassRequest: QuantityPassRequest[], timePassRequest: TimePassRequest[]) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.ageDiscountsRequest = ageDiscountsRequest;
    this.quantityPassRequest = quantityPassRequest;
    this.timePassRequest = timePassRequest;
  }
}
