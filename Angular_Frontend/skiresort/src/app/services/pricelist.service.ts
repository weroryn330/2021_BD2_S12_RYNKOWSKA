import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PricelistResponse} from "../classes/pricelist-response";
import {PriceListRequest} from "../classes/pricelist-request";

@Injectable({
  providedIn: 'root'
})
export class PricelistService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  endPoint = 'http://localhost:8080/api/'

  constructor(private http: HttpClient) {}

  getPricelist(): Observable<any> {
    return this.http.get<PricelistResponse>(this.endPoint + 'priceList/current');
  }

  editPricelist(priceListRequest: PriceListRequest): Observable<any> {
    return this.http.post(this.endPoint + 'priceList/edit/priceList', priceListRequest, this.httpOptions );
  }
}
