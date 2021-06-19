import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {SkiliftService} from "../../../../services/skilift.service";
import {ScheduleUpdateRequest} from "../../../../classes/schedule-update-request";

@Component({
  selector: 'app-single-schedule-element',
  templateUrl: './single-schedule-element.component.html',
  styleUrls: ['./single-schedule-element.component.css']
})
export class SingleScheduleElementComponent implements OnInit {
  @Input() schedule: any;
  @Input() index: any;
  @Output() newScheduleModificationEvent = new EventEmitter<[ any, number]>();
  form: any = {
    opensTime: null,
    closesTime: null
  }

  constructor(private skiliftService: SkiliftService) { }

  ngOnInit(): void {
    this.form.opensTime = this.schedule.opensTime;
    this.form.closesTime = this.schedule.closesTime;
  }

  onSubmit() {
    this.skiliftService.updateSchedule(new ScheduleUpdateRequest(this.form.opensTime,
      this.form.closesTime, this.schedule.skiLiftId, this.schedule.skiLiftScheduleId)).subscribe( data => {
        console.log(data);
        this.newScheduleModificationEvent.emit([data,this.index]);
        alert("Pomyślnie zmodyfikowano rozkład dla wyciągu "+ this.schedule.skiLiftName);
    }, error => {
      console.log(error.error.message);
      alert("Niepomyślnie zmodyfikowano rozkład dla wyciągu "+ this.schedule.skiLiftName);
    })
  }
}
