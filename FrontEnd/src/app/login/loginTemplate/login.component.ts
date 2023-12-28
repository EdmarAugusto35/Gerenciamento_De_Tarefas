import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthServiceService} from "../../auth-service.service";



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';
  errorMessage: string = '';
  constructor( private router:Router, private authService:AuthServiceService) { }

  ngOnInit(): void {
  }

  login(){
    this.authService.login(this.username, this.password).subscribe(
      (data) =>{
        this.router.navigate(['/home']);
      },
      (error) =>{
        this.errorMessage = 'Credenciais inválidas. Tente novamente.';
      }
    );
  }
}
