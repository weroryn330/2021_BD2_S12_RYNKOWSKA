import { Component, OnInit } from '@angular/core';
import {SkiliftService} from "../../../services/skilift.service";

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.css']
})
export class SchedulesComponent implements OnInit {
  skiliftSchedulesList: any;
  constructor(private skiliftService: SkiliftService) { }

  ngOnInit(): void {
    this.getCurrentSchedules();
  }

  getCurrentSchedules() {
    this.skiliftService.getCurrentSchedules().subscribe( data => {
      console.log(data);
      this.skiliftSchedulesList = data;
    }, error => {
      this.skiliftSchedulesList = [];
      console.log(error.error.message);
    })
  }

  updateSchedule(scheduleAndIndex: any) {
    this.skiliftSchedulesList[scheduleAndIndex[1]] = scheduleAndIndex[0];
  }
}
