import {PassRequest} from "./pass-request";

export class InvoiceRequest {
  invoiceDate: string;
  billingAddress: string;
  billingCity: string;
  billingState: string;
  billingCountry: string;
  billingPostalCode: string;
  total: number;
  passList: PassRequest[];


  constructor(invoiceDate: string, billingAddress: string, billingCity: string, billingState: string, billingCountry: string, billingPostalCode: string, total: number, passList: PassRequest[]) {
    this.invoiceDate = invoiceDate;
    this.billingAddress = billingAddress;
    this.billingCity = billingCity;
    this.billingState = billingState;
    this.billingCountry = billingCountry;
    this.billingPostalCode = billingPostalCode;
    this.total = total;
    this.passList = passList;
  }
}
