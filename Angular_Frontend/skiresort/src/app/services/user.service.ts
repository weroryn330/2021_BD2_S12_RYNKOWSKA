import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {RegistrationRequest} from "../classes/registration-request";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  endPoint = 'http://localhost:8080/api/user'
  constructor(private http: HttpClient) { }

  changePassword(userData: RegistrationRequest): Observable<any> {
    return this.http.put<RegistrationRequest>(this.endPoint + '/password',
      userData ,this.httpOptions)
  }

  changeEmail(userData: RegistrationRequest): Observable<any> {
    return this.http.put<RegistrationRequest>(this.endPoint + '/email',
      userData ,this.httpOptions)
  }

  changeUserInfo(newUserInfo: RegistrationRequest): Observable<any> {
    return this.http.put<RegistrationRequest>(this.endPoint,
      newUserInfo ,this.httpOptions)
  }
}
