import {TimePassResponse} from "./time-pass-response";
import {AgeDiscountResponse} from "./age-discount-response";
import {QuantityPassResponse} from "./quantity-pass-response";

export class PricelistResponse {
  ageDiscountsList: AgeDiscountResponse[] = [];
  timePassesList: TimePassResponse[] = [];
  quantityPassesList: QuantityPassResponse[] = [];

  constructor(ageDiscountsList: AgeDiscountResponse[], timePassesList: TimePassResponse[], quantityPassesList: QuantityPassResponse[]) {
    this.ageDiscountsList = ageDiscountsList;
    this.timePassesList = timePassesList;
    this.quantityPassesList = quantityPassesList;
  }
}
