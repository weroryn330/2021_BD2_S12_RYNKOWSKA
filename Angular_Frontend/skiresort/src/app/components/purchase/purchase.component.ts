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
import {InvoiceRequest} from "../../classes/invoice-request";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css',
    '../registration/registration.component.css']
})
export class PurchaseComponent implements OnInit {
  passForms: number[] = [];
  routeData: any = {
    pass: null,
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
    if (this.routeData.pass == null || this.routeData.pricelist == null) {
      this.getPricelist();
    } else {
      this.pricelist = this.routeData.pricelist;
      this.passForms.push(1);
    }
  }

  addPassRequest(request: PassRequest) {
    this.passRequestsList.push(request);
    if (parseInt(this.form.total) == 0) {
      this.form.total = request.unitPrice;
    } else {
      this.form.total = (parseInt(this.form.total) + request.unitPrice) as number;
    }
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
        this.pricelist.ageDiscountsList = data.ageDiscountsResponse;
        this.pricelist.quantityPassesList = data.quantityPassResponse;
        this.pricelist.timePassesList = data.timePassResponse;
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
    console.log(formatDate(new Date(), 'yyyy-MM-dd\'T\'HH:mm:ss.SSS', 'en-US'));
    this.invoiceService.addInvoice(new InvoiceRequest(formatDate(new Date(),
      'yyyy-MM-dd\'T\'HH:mm:ss.SSS', 'en-US'), this.form.address,
      this.form.city, this.form.voivodeship, this.form.country, this.form.postalCode,
      this.form.total, this.passRequestsList)).subscribe(data => {
        alert("Dokonano pomyślnego zakupu!");
        this.router.navigateByUrl('/profile/passes');
      },
      error => {
        alert("Transakcja przebiegła niepomyślnie!");
      })
  }
}
