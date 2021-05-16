import {Component} from '@angular/core';
import {NavigationStart, Router, RoutesRecognized} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentHeader = "homeHeader";
  currentWrapper = "wrapper";

  constructor(private router: Router) {
    this.router.events.subscribe(event => {

        switch (this.router.url) {
          case "/login":
            this.currentHeader = "loginHeader";
            this.currentWrapper = "wrapperL";
            break;
          case "/about":
            this.currentHeader = "aboutHeader";
            this.currentWrapper = "wrapper";
            break;
          default:
            this.currentHeader = "homeHeader";
            this.currentWrapper = "wrapper";
        }
      }
    )
  }
}
