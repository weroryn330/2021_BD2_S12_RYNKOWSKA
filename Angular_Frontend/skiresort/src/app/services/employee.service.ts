import {Injectable} from '@angular/core';
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
      registrationRequest, this.httpOptions)
  }

  getEmployees() {
    return this.http.get(this.endPoint + 'owner/employees');
  }


  updateEmployeeRole(email: string, role: string) {
    return this.http.put(this.endPoint + 'owner/editAccount/editRole?email=' + email + '&roleName=' + role, '', this.httpOptions);
  }

  updateEmployeePassword(registrationRequest: RegistrationRequest) {
    return this.http.put<RegistrationRequest>(this.endPoint + 'owner/editAccount/password',registrationRequest, this.httpOptions);
  }

  updateEmployeeEmail(registrationRequest: RegistrationRequest) {
    return this.http.put<RegistrationRequest>(this.endPoint + 'owner/editAccount/email',registrationRequest, this.httpOptions);
  }

  updateEmployeePersonalData(registrationRequest: RegistrationRequest) {
    return this.http.put<RegistrationRequest>(this.endPoint + 'owner/editAccount/details',registrationRequest, this.httpOptions);
  }
}
