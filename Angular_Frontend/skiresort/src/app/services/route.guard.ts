import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenService} from "./token.service";

@Injectable({
  providedIn: 'root'
})
export class RouteGuard implements CanActivate {
  constructor(private token: TokenService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data.expectedRole;
    if(this.token.getToken()==null) {
      if(expectedRole == 'ROLE_NONE') {
        return true;
      }
      else{
        this.router.navigateByUrl('/welcome');
        return false;
      }
    }
    const roles = this.token.getUser().roleList;
    if(!roles.includes(expectedRole)){
      this.router.navigateByUrl('/welcome');
      return false;
    }
    return true;
  }

}
