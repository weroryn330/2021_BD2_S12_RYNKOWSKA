import {Component, OnInit} from '@angular/core';
import {PricelistService} from "../../../services/pricelist.service";
import {PriceListRequest} from "../../../classes/pricelist-request";
import {AgeDiscountRequest} from "../../../classes/age-discount-request";
import {QuantityPassRequest} from "../../../classes/quantity-pass-request";
import {TimePassRequest} from "../../../classes/time-pass-request";

@Component({
  selector: 'app-pricelist-edit',
  templateUrl: './pricelist-edit.component.html',
  styleUrls: ['./pricelist-edit.component.css']
})
export class PricelistEditComponent implements OnInit {
  ageDiscountsList: AgeDiscountRequest[] = [];
  quantityPassesList: QuantityPassRequest[] = [];
  timePassesList: TimePassRequest[] = [];
  discountsChanged = false;
  quantityPassesChanged = false;
  timePassesChanged = false;

  constructor(private pricelistService: PricelistService) {
  }

  ngOnInit(): void {
    this.getActivePricelist();
  }

  private getActivePricelist() {
    this.pricelistService.getPricelist().subscribe(data => {
        console.log(data);
        this.ageDiscountsList = data.ageDiscountsResponse;
        this.quantityPassesList = data.quantityPassResponse;
        this.timePassesList = data.timePassResponse;
      },
      error => {
        console.log(error.error);
      })
  }

  editPricelist() {
    if (this.discountsChanged && this.quantityPassesChanged && this.timePassesChanged) {
      this.pricelistService.editPricelist(new PriceListRequest('2000-01-01', '2000-01-01',
        this.ageDiscountsList, this.quantityPassesList, this.timePassesList)).subscribe(data => {
          console.log(data);
          this.ageDiscountsList = data.ageDiscountsResponse;
          this.quantityPassesList = data.quantityPassResponse;
          this.timePassesList = data.timePassResponse;
          alert("Pomyślnie zmodyfikowano cennik");
        },
        error => {
          console.log(error);
          alert("Błąd podczas modyfikacji cennika");
        })
    }
  }

  editDiscounts(newDiscounts: any) {
    this.ageDiscountsList = newDiscounts;
    this.discountsChanged = true;
    console.log('NEW DISCOUNTS');
  }

  editQuantityPasses(newQuantityPasses: any) {
    this.quantityPassesList = newQuantityPasses;
    this.quantityPassesChanged = true;
    console.log('NEW QUANTITY PASSES');
  }

  editTimePasses(newTimePasses: any) {
    this.timePassesList = newTimePasses;
    this.timePassesChanged = true;
    console.log('NEW TIME PASSES');
  }
}
