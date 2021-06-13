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
  roles: string[] = [];
  currentNavContainer = 'navContainer';
  currentLogo = 'logo';
  currentNav = 'mainNav';

  constructor(private router: Router, private token: TokenService) {
    this.router.events.subscribe(event => {
        this.ngOnInit();
        this.currentContainer = "container";
        this.currentLogo = 'logo';
        this.currentNav = 'mainNav';
        this.currentNavContainer = 'navContainer';
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
          case "/pricelist":
            this.currentContainer = "pricelistContainer";
            break;
          default:
            if (this.router.url.match('profile')) {
              this.currentContainer = "profileContainer";
              break;
            } else if (this.router.url.match('management')) {
              this.currentContainer = "technicContainer";
              this.currentLogo = 'logoW';
              this.currentNav = 'mainNavW';
              this.currentNavContainer = 'navContainerW';
              break;
            }
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
    const mainNavW = document.querySelector('.mainNavW') as HTMLElement;
    if (mainNav !== null) {
      mainNav.style.display = 'flex';
      mainNav.style.top = '0';
    }
    if (mainNavW !== null) {
      mainNavW.style.display = 'flex';
      mainNavW.style.top = '0';
    }
  }

  closeMenu() {
    const mainNav = document.querySelector('.mainNav') as HTMLElement;
    const mainNavW = document.querySelector('.mainNavW') as HTMLElement;
    if (mainNav !== null) {
      mainNav.style.top = '-100%';
    }
    if (mainNavW !== null) {
      mainNavW.style.top = '-100%';
    }
  }

  logout() {
    this.token.signOut();
    this.loggedIn = false;
    window.location.reload();
  }
}
