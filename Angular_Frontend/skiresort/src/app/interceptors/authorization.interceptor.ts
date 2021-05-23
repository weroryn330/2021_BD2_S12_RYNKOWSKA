import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {TokenService} from "../services/token.service";

@Injectable()
export class AuthorizationInterceptor implements HttpInterceptor {

  constructor(private token: TokenService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authRequest = request;
    const token = this.token.getToken();
    if(token != null) {
      authRequest = request.clone({headers: request.headers.set('Authorization', 'Bearer ' + token)});
    }
    return next.handle(authRequest);
  }
}
export const authInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthorizationInterceptor, multi: true }
];
