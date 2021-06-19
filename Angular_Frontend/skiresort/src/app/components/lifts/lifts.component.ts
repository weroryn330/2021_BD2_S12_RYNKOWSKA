import { Component, OnInit } from '@angular/core';
import {SkiliftService} from "../../services/skilift.service";
import {PassResponse} from "../../classes/pass-response";
import {SkiliftResponse} from "../../classes/skilift-response";

@Component({
  selector: 'app-lifts',
  templateUrl: './lifts.component.html',
  styleUrls: ['./lifts.component.css',
    './../about-us/about-us.component.css']
})
export class LiftsComponent implements OnInit {
  skiliftsList: SkiliftResponse[] = [];
  constructor(private skiliftService: SkiliftService) { }

  ngOnInit(): void {
    this.getSkiLifts();
  }

  private getSkiLifts() {
    this.skiliftService.getSkilifts().subscribe( (data: any) => {
        console.log(data);
        data.map((skilift: any) => this.skiliftsList.push(new SkiliftResponse(skilift.name, skilift.height,
          skilift.opened, skilift.peopleUsingLift, skilift.skiLiftScheduleResponse.opensTime,
          skilift.skiLiftScheduleResponse.closesTime))
        )
    },
      error => {
        this.skiliftsList = [];
        console.log(error.error);
      })
  }
}
