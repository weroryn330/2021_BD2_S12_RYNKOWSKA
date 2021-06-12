import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PassService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  endPoint = 'http://localhost:8080/api'
  constructor(private http: HttpClient) { }

  getActivePasses(): Observable<any> {
    return this.http.get(this.endPoint + "/employee/allPasses");
  }
}
