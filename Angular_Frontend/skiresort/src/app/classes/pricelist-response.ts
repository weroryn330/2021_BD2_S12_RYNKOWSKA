import {TimePassResponse} from "./time-pass-response";
import {AgeDiscountResponse} from "./age-discount-response";
import {QuantityPassResponse} from "./quantity-pass-response";

export class PricelistResponse {
  startDate: string;
  endDate: string;
  ageDiscountsList: AgeDiscountResponse[];
  timePassesList: TimePassResponse[];
  quantityPassesList: QuantityPassResponse[];


  constructor(startDate: string, endDate: string, ageDiscountsList: AgeDiscountResponse[], timePassesList: TimePassResponse[], quantityPassesList: QuantityPassResponse[]) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.ageDiscountsList = ageDiscountsList;
    this.timePassesList = timePassesList;
    this.quantityPassesList = quantityPassesList;
  }
}
