import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";

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

  register(firstName: string, lastName: string, country: string, email: string, phone: string,
           voivodeship: string, postalCode: string, city: string, address: string, password: string): Observable<any> {
    return this.http.post(this.endPoint + 'register', {
      firstName,
      lastName,
      address,
      city,
      voivodeship,
      country,
      postalCode,
      phone,
      email,
      password
    },this.httpOptions)
  }
}
