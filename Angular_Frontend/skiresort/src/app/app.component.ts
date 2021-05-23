import {Component, OnInit} from '@angular/core';
import {NavigationStart, Router, RoutesRecognized} from "@angular/router";
import {TokenService} from "./services/token.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  currentContainer = "container";
  loggedIn = false;
  roles = [];

  constructor(private router: Router, private token: TokenService) {
    this.router.events.subscribe(event => {
        this.ngOnInit();
        switch (this.router.url) {
          case "/login":
            this.currentContainer = "loginContainer";
            break;
          case "/register":
            this.currentContainer = "registrationContainer";
            break;
          case "/about":
          case "/lifts":
          case "/contact":
            this.currentContainer = "aboutContainer";
            break;
          default:
            this.currentContainer = "container";
        }
      }
    )
  }

  ngOnInit(): void {
    if (this.token.getToken() != null) {
      this.loggedIn = true;
      this.roles = this.token.getUser().roleList;
    }
  }

  openMenu() {
    const mainNav = document.querySelector('.mainNav') as HTMLElement;
    mainNav.style.display = 'flex';
    mainNav.style.top = '0';
  }

  closeMenu() {
    const mainNav = document.querySelector('.mainNav') as HTMLElement;
    mainNav.style.top = '-100%';
  }

  logout() {
    this.token.signOut();
    this.loggedIn = false;
    this.ngOnInit();
  }
}
