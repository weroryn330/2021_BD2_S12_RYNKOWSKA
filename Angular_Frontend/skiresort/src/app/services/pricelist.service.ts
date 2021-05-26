import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PricelistResponse} from "../classes/pricelist-response";

@Injectable({
  providedIn: 'root'
})
export class PricelistService {

  endPoint = 'http://localhost:8080/api/pricelist'

  constructor(private http: HttpClient) {}

  getPricelist(): Observable<any> {
    return this.http.get<PricelistResponse>(this.endPoint);
  }
}
