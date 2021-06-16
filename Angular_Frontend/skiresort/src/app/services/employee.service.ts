import { Injectable } from '@angular/core';
import {RegistrationRequest} from "../classes/registration-request";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  endPoint = 'http://localhost:8080/api/'

  constructor(private http: HttpClient) {
  }

  registerEmployee(registrationRequest: RegistrationRequest, employeeType: string) {
    return this.http.post<RegistrationRequest>(this.endPoint + 'owner/employees/add?roleName=' + employeeType,
      registrationRequest ,this.httpOptions)
  }

  getEmployees() {
    return this.http.get(this.endPoint + 'owner/employees');
  }

  updateEmployee(registrationRequest: RegistrationRequest, employeeType: string, employeeId: number) {
    return this.http.put<RegistrationRequest>(this.endPoint + 'owner/employees/'+ employeeId +'?roleName=' + employeeType,
      registrationRequest ,this.httpOptions)
  }
}
