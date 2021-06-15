import {Component, OnInit} from '@angular/core';
import {UserResponse} from "../../../classes/user-response";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  usersList: UserResponse[] = [];
  page = 1;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers() {
    this.userService.getUsers().subscribe(data => {
        console.log(data);
        data.map((user: any) => this.usersList.push(new UserResponse(user)));
      },
      error => {
        this.usersList = [];
        alert("Brak użytkowników do wyświetlenia");
      })
  }
}
