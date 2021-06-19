import {Component, OnInit} from '@angular/core';
import {PassService} from "../../services/pass.service";
import * as fileSaver from "file-saver";

@Component({
  selector: 'app-user-report',
  templateUrl: './user-report.component.html',
  styleUrls: ['./user-report.component.css']
})
export class UserReportComponent implements OnInit {
  form: any = {
    startDate: null,
    endDate: null
  }
  passesList: any;
  page = 1;

  constructor(private passService: PassService) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.passService.getPassesUsedBetweenDates(this.form.startDate, this.form.endDate).subscribe((data: any) => {
        console.log(data);
        this.passesList = data;
      },
      error => {
        this.passesList = [];
        alert("Nie znaleziono raportów");
      })
  }

  downloadReport(passId: number, startDate: string, endDate: string) {
  this.passService.getReportCSV(passId, startDate, endDate).subscribe((data: any) => {
      let blob: any = new Blob([data], {type: 'text/csv'});
      fileSaver.saveAs(blob, 'raport' + passId + '.csv');
    }, error => {
      alert("Coś poszło nie tak...");
    })
  }
}
