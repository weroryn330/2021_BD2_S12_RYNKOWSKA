import {Component, OnInit} from '@angular/core';
import {PassRequest} from "../../classes/pass-request";
import {PricelistService} from "../../services/pricelist.service";
import {PricelistResponse} from "../../classes/pricelist-response";
import {TokenService} from "../../services/token.service";
import {AgeDiscountResponse} from "../../classes/age-discount-response";
import {TimePassResponse} from "../../classes/time-pass-response";
import {QuantityPassResponse} from "../../classes/quantity-pass-response";
import {InvoiceService} from "../../services/invoice.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {
  passForms: number[] = [];
  routeData: any = {
    pass: null,
    discount: null,
    pricelist: null
  }

  form: any = {
    country: null,
    voivodeship: null,
    postalCode: null,
    city: null,
    address: null,
    total: 0,
    useUserData: null
  }

  userData: any;
  pricelist: PricelistResponse = {} as PricelistResponse;
  passRequestsList: PassRequest[] = [];
  isPassFormValid = false;

  constructor(private pricelistService: PricelistService, private router: Router,
              private invoiceService: InvoiceService, private token: TokenService) {
  }

  ngOnInit(): void {
    this.userData = this.token.getUser();
    this.routeData = history.state;
    if (this.routeData.pass == null || this.routeData.discount == null || this.routeData.pricelist == null) {
      //this.getPricelist();
      //  STATIC TEST
      this.pricelist = {
        ageDiscountsList: [new AgeDiscountResponse(5, 10, 33)],
        timePassesList: [new TimePassResponse(3, 20),
          new TimePassResponse(24, 50), new TimePassResponse(48, 75)],
        quantityPassesList: [new QuantityPassResponse(5, 20),
          new QuantityPassResponse(10, 60), new QuantityPassResponse(20, 100)]
      };
    } else {
      this.pricelist = this.routeData.pricelist;
      this.passForms.push(1);
    }
  }

  addPassRequest(request: PassRequest) {
    this.passRequestsList.push(request);
    this.form.total = parseInt(this.form.total) + request.unitPrice;
  }

  checkPassFormValidation(validate: boolean) {
    this.isPassFormValid = validate;
  }

  addPassForm() {
    this.passForms.push(1);
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


  insertDash(): void {
    if (this.form.postalCode) {
      if (this.form.postalCode.length === 2) {
        this.form.postalCode = this.form.postalCode + '-'
      }
    }
  }

  fillData(value: any) {
    if (value) {
      this.form.country = this.userData.country;
      this.form.voivodeship = this.userData.voivodeship;
      this.form.postalCode = this.userData.postalCode;
      this.form.city = this.userData.city;
      this.form.address = this.userData.address;
    } else {
      this.form.country = null;
      this.form.voivodeship = null;
      this.form.postalCode = null;
      this.form.city = null;
      this.form.address = null;
    }
  }

  onSubmit() {
    // TODO
    this.router.navigateByUrl('/profile');
  }
}
