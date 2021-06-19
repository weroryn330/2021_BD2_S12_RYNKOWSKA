import {Component, OnInit} from '@angular/core';
import {PricelistService} from "../../../services/pricelist.service";
import {PricelistResponse} from "../../../classes/pricelist-response";
import {AgeDiscountResponse} from "../../../classes/age-discount-response";
import {QuantityPassResponse} from "../../../classes/quantity-pass-response";
import {TimePassResponse} from "../../../classes/time-pass-response";

@Component({
  selector: 'app-pricelist-edit',
  templateUrl: './pricelist-edit.component.html',
  styleUrls: ['./pricelist-edit.component.css']
})
export class PricelistEditComponent implements OnInit {
  ageDiscountsList: AgeDiscountResponse[] = [];
  quantityPassesList: QuantityPassResponse[] = [];
  timePassesList: TimePassResponse[] = [];
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
      this.pricelistService.editPricelist(new PricelistResponse(this.ageDiscountsList,
        this.timePassesList, this.quantityPassesList)).subscribe(data => {
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
