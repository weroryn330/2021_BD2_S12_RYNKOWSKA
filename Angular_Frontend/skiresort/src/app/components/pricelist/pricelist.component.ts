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
  //  STATIC TEST
   /* pricelist: PricelistResponse = {
    ageDiscountsList: [new AgeDiscountResponse(5, 10, 33)],
    timePassesList: [ new TimePassResponse(3,20),new TimePassResponse(24,50),new TimePassResponse(48,75)],
    quantityPassesList: [ new QuantityPassResponse(5,20),new QuantityPassResponse(10,60),new QuantityPassResponse(20,100)]
  };*/
    isUser = false;

  constructor(private pricelistService: PricelistService, private router: Router, private token: TokenService) {
  }

  ngOnInit(): void {
    this.getPricelist();
    if(!this.token.getToken()){
      this.isUser = false;
    }
    else{
      this.isUser = this.token.getUser().roleList.includes("ROLE_USER");
    }
  }

  goToPurchase(pricelistPass: PricelistPass, discount: AgeDiscountResponse) {
    this.router.navigateByUrl('/profile/purchase', {state: {pass: pricelistPass, discount: discount, pricelist: this.pricelist}});
  }

  goToLogin() {
    this.router.navigateByUrl('/login');
  }

  private getPricelist(): void {
    this.pricelistService.getPricelist().subscribe(data => {
        console.log(data);
        this.pricelist = data;
      },
      error => {
        console.log(error);
      })
  }

  calculateDiscountPrice(price: number): number {
    const discount = this.pricelist.ageDiscountsList[0].percentage;
    return price*((100-discount)/100);
  }
}
