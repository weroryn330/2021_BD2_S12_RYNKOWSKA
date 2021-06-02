import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../services/token.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css',
    './../about-us/about-us.component.css'
  ]
})
export class ProfileComponent implements OnInit {
user: any;
  constructor(private token: TokenService) {
  }

  ngOnInit(): void {
    this.user = this.token.getUser();
  }

}
