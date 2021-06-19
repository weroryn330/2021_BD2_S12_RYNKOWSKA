import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PricelistResponse} from "../classes/pricelist-response";

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

  editPricelist(newPricelist: PricelistResponse): Observable<any> {
    return this.http.put(this.endPoint, newPricelist, this.httpOptions );
  }
}
