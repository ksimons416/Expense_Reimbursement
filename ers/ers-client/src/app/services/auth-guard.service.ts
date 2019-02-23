import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
// used as a route guard.
// if returning false the route will not be loaded
export class AuthGuardService implements CanActivate {

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (localStorage.getItem('authToken')) {
      return true;
    }
    this.router.navigate(['/ers'], {queryParams: {returnUrl: state.url}});
    return false;
  }
}
