import { Component, OnInit } from '@angular/core';
import {TokenService} from "../../services/token.service";

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css']
})
export class ManagementComponent implements OnInit {
userRoles: string[] = [];
  constructor(private token: TokenService) { }

  ngOnInit(): void {
    this.userRoles = this.token.getUser().roleList;
  }

}
