import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SkiliftService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  endPoint = 'http://localhost:8080/api'
  constructor(private http: HttpClient) { }

  getTechnicalSkilifts() {
    return this.http.get(this.endPoint + '/technical_employee/all');
  }

  getSkilifts() {
    return this.http.get(this.endPoint + '/mockup_usage');
  }

  changeSkiliftState(idSkiLift: number) {
    return this.http.put(this.endPoint + '/technical_employee/' + idSkiLift, this.httpOptions );
  }
}
