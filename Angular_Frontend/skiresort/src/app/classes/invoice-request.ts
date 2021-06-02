export class InvoiceRequest {
  billingCountry: string;
  billingState: string;
  billingPostalCode: string;
  billingCity: string;
  billingAddress: string;
  total: number;


  constructor(billingCountry: string, billingVoivodeship: string, billingPostalCode: string, billingCity: string, billingAddress: string, total: number) {
    this.billingCountry = billingCountry;
    this.billingState = billingVoivodeship;
    this.billingPostalCode = billingPostalCode;
    this.billingCity = billingCity;
    this.billingAddress = billingAddress;
    this.total = total;
  }
}
