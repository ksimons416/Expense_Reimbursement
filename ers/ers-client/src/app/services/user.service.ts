import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {Users} from '../models/Users';
import {HttpClient} from "@angular/common/http";
import { HttpHeaders } from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

}
