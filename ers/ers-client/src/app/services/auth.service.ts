import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authUrl = 'http://localhost:8080/ers/auth';


  constructor(
    private http: HttpClient
    ) { }

  isLoggedIn(): boolean {
    return localStorage.getItem('authToken') !== null;
  }

  getLoggedInUser(){
    let userJson = localStorage.getItem('authToken');
    if(userJson === null)return null;
    let user = JSON.parse(userJson);
    return user;
  }

  hasRoles(roles: Array<string>): boolean {
    const tokenRoles: Array<string> = JSON.parse(localStorage.getItem('authToken')).roles;
    return roles.every(role => tokenRoles.indexOf(role) >= 0);
  }

  authenticate(username: string, password: string, success, fail) {
    return this.http.post<any>(this.authUrl,
      JSON.stringify({email: username, password: password}),
      {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .toPromise()
      .then((resp) => {
        localStorage.setItem('authToken', JSON.stringify(resp));
        success();
      },
      (err) => {
        fail(err);
      });
  }
}
