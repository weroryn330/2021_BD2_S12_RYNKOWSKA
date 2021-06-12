import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PassResponse} from "../classes/pass-response";

@Injectable({
  providedIn: 'root'
})
export class PassService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  endPoint = 'http://localhost:8080/api'
  constructor(private http: HttpClient) { }

  getUserPasses(): Observable<PassResponse[]> {
    return this.http.get<PassResponse[]>(this.endPoint + '/passes');
  }

  getActivePasses(): Observable<any> {
    return this.http.get(this.endPoint + "/employee/allPasses");
  }

  getQR(passId: number): Observable<any> {
    return this.http.get(this.endPoint + '/passes/qr/' + passId, {responseType: 'blob'});
  }
}
