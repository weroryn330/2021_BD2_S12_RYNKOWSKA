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
  endPoint = 'http://localhost:8080/api'
  constructor(private http: HttpClient) { }

  addInvoice(invoiceRequest: InvoiceRequest): Observable<any> {
    return this.http.post<InvoiceRequest>(this.endPoint + '/invoices',
      invoiceRequest ,this.httpOptions);
  }
  getUserInvoices(): Observable<any> {
    return this.http.get(this.endPoint + '/invoices');
  }

  getUserInvoicesForOwner(email: string): Observable<any> {
    return this.http.get(this.endPoint + '/owner/invoices/' + email);
  }
  getUserInvoicePDF(invoiceId: number): Observable<any> {
    return this.http.get(this.endPoint + '/invoiceReport/' + invoiceId, {responseType: 'blob'});
  }

  getInvoices(): Observable<any> {
    return this.http.get(this.endPoint + '/owner/invoices/all');
  }

  getInvoicesBetweenDates(startDate: string, endDate: string) {
    return this.http.get(this.endPoint + '/owner/invoices/dates'+'?startDate=' + startDate + '&endDate=' + endDate);

  }
}
