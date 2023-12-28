import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  apiURL: string = environment.apiURLBase + '/api/auth';
  constructor(private http: HttpClient) { }

  login(username: string, password: string){
    const credenciais = {username, password}
    return this.http.post(`${this.apiURL}/login`, credenciais);
  }
}
