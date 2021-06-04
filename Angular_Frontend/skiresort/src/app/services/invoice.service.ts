import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {InvoiceRequest} from "../classes/invoice-request";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  endPoint = 'http://localhost:8080/api/invoices'
  constructor(private http: HttpClient) { }

  addInvoice(invoiceRequest: InvoiceRequest): Observable<any> {
    console.log(invoiceRequest);
    return this.http.post<InvoiceRequest>(this.endPoint,
      invoiceRequest ,this.httpOptions);
  }
}
