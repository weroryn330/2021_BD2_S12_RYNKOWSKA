import {Component} from '@angular/core';
import {NavigationStart, Router, RoutesRecognized} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentContainer = "container";


  constructor(private router: Router) {
    this.router.events.subscribe(event => {

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

  openMenu() {
    const mainNav = document.querySelector('.mainNav') as HTMLElement;
    mainNav.style.display = 'flex';
    mainNav.style.top = '0';
  }

  closeMenu() {
    const mainNav = document.querySelector('.mainNav') as HTMLElement;
    mainNav.style.top = '-100%';
  }
}
