import {Component, OnInit} from '@angular/core';
import {PricelistResponse} from "../../classes/pricelist-response";
import {PricelistService} from "../../services/pricelist.service";
import {Router} from "@angular/router";
import {PricelistPass} from "../../classes/pricelist-pass";
import {AgeDiscountResponse} from "../../classes/age-discount-response";
import {TokenService} from "../../services/token.service";
import {TimePassResponse} from "../../classes/time-pass-response";
import {QuantityPassResponse} from "../../classes/quantity-pass-response";

@Component({
  selector: 'app-pricelist',
  templateUrl: './pricelist.component.html',
  styleUrls: ['./pricelist.component.css']
})
export class PricelistComponent implements OnInit {
  pricelist: PricelistResponse = {} as PricelistResponse;
  isUser = false;

  constructor(private pricelistService: PricelistService, private router: Router, private token: TokenService) {
  }

  ngOnInit(): void {
    this.getPricelist();
    if (!this.token.getToken()) {
      this.isUser = false;
    } else {
      this.isUser = this.token.getUser().roleList.includes("ROLE_USER");
    }
  }

  goToPurchase(pricelistPass: PricelistPass) {
    this.router.navigateByUrl('/profile/purchase', {state: {pass: pricelistPass, pricelist: this.pricelist}});
  }

  goToLogin() {
    this.router.navigateByUrl('/login');
  }

  private getPricelist(): void {
    this.pricelistService.getPricelist().subscribe(data => {
        console.log(data);
        this.pricelist.ageDiscountsList = data.ageDiscountsResponse;
        this.pricelist.quantityPassesList = data.quantityPassResponse;
        this.pricelist.timePassesList = data.timePassResponse;
        console.log(this.pricelist);
      },
      error => {
        console.log(error);
      })
  }

  calculateDiscountPrice(price: number, discountNumber: number): number {
    const discount = this.pricelist.ageDiscountsList[discountNumber].percentage;
    return price * ((100 - discount) / 100);
  }
}
