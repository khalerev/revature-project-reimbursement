import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Router } from "@angular/router";


@Component({
    selector: 'app-home',
    template: `
        <div>
            Welcome {{name}}
            <button (click)="logout()">Logout</button>
            <ul>
            <li><a routerLink="">Home</a></li>
            <!-- <li><a routerLink="menus">Menus</a></li> -->
            <li><a routerLink="tickets">Tickets</a></li>
            </ul>
            <router-outlet></router-outlet>
        </div>
    `
})
export class HomeComponent{
    name:string = localStorage.getItem("username") || "";
    constructor(private router:Router, private http:HttpClient){}

    logout(){
        localStorage.clear()
        this.router.navigate(['login'])
    }

}