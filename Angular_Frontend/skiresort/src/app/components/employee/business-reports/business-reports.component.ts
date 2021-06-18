import { Component, OnInit } from '@angular/core';
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

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
  }

  downloadBusinessReportPDF(startDate: string, endDate: string) {
    this.employeeService.getBusinessReportPDF(startDate, endDate).subscribe((data: any) => {
      let blob: any = new Blob([data], {type: 'application/pdf'});
      fileSaver.saveAs(blob, 'raport-biznesowy_' + startDate + '_' + endDate + '.pdf');
    }, error => {
      alert("Coś poszło nie tak...");
    })
  }
  onSubmit() {
    this.downloadBusinessReportPDF(this.form.startDate, this.form.endDate);
  }
}
