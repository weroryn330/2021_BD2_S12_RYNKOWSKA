import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RegistrationRequest} from "../classes/registration-request";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
endPoint = 'http://localhost:8080/api/'

  constructor(private http: HttpClient) {
  }

  register(registrationRequest: RegistrationRequest): Observable<any> {
    return this.http.post<RegistrationRequest>(this.endPoint + 'register',
      registrationRequest ,this.httpOptions)
  }
}
