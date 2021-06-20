import {Component, OnInit} from '@angular/core';
import * as fileSaver from "file-saver";
import {EmployeeService} from "../../../services/employee.service";

@Component({
  selector: 'app-business-reports',
  templateUrl: './business-reports.component.html',
  styleUrls: ['./business-reports.component.css']
})
export class BusinessReportsComponent implements OnInit {
  form: any = {
    startDate: null,
    endDate: null
  }
  todayDate: string = new Date().toString();

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit(): void {
  }

  downloadBusinessReportPDF(startDate: string, endDate: string) {
    this.employeeService.getBusinessReportPDF(startDate, endDate).subscribe((data: any) => {
      let blob: any = new Blob([data], {type: 'application/pdf'});
      fileSaver.saveAs(blob, 'raport-biznesowy_' + startDate.replace('-', '') +
        '_' + endDate.replace('-', '') + '.pdf');
    }, error => {
      alert("Coś poszło nie tak...");
    })
  }

  invalidStartDate() {
    return (new Date(this.form.startDate) < new Date('2010-01-01')) ||
      (new Date(this.form.startDate) > new Date())
  }

  invalidEndDate() {
    return (new Date(this.form.endDate) < new Date('2010-01-01')) ||
      (new Date(this.form.endDate) > new Date())
  }

  invalidDatesInput(){
    return this.invalidStartDate() || this.invalidEndDate();
  }

  onSubmit() {
    this.downloadBusinessReportPDF(this.form.startDate, this.form.endDate);
  }
}
