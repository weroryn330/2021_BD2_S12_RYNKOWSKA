import { Component, OnInit } from '@angular/core';
import {TokenService} from "../../services/token.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css']
})
export class WelcomePageComponent implements OnInit {
  roles = [];
  username = '';
  loggedIn = false;
  constructor(private token: TokenService, private router: Router) { }

  ngOnInit(): void {
      if (this.token.getToken() != null) {
        this.loggedIn = true;
        this.roles = this.token.getUser().roleList;
        this.username = this.token.getUser().firstName + ' ' + this.token.getUser().lastName;
      }
      else{
        this.loggedIn = false;
      }
  }

  goToPricelist() {
    this.router.navigateByUrl("/pricelist");
  }
}
