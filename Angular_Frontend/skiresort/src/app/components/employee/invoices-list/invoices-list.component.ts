import {Component, OnInit} from '@angular/core';
import {InvoiceService} from "../../../services/invoice.service";
import * as fileSaver from "file-saver";

@Component({
  selector: 'app-invoices-list',
  templateUrl: './invoices-list.component.html',
  styleUrls: ['./invoices-list.component.css']
})
export class InvoicesListComponent implements OnInit {
  invoicesList: any;
  form: any = {
    startDate: null,
    endDate: null
  }
  page = 1;

  constructor(private invoiceService: InvoiceService) {
  }

  ngOnInit(): void {
  }

  getInvoicesBetweenDates() {
    this.invoiceService.getInvoicesBetweenDates(this.form.startDate, this.form.endDate).subscribe((data: any) => {
        this.invoicesList = data;
        console.log(data);
      },
      error => {
        this.invoicesList = [];
        alert("Coś poszło nie tak...");
      })
  }

  downloadInvoicePDF(invoiceId: number, invoiceDate: string) {
    this.invoiceService.getUserInvoicePDF(invoiceId).subscribe((data: any) => {
      let blob: any = new Blob([data], {type: 'application/pdf'});
      fileSaver.saveAs(blob, 'faktura_' + invoiceId + '_' +
        invoiceDate.replace('-', '') + '.pdf');
    }, error => {
      alert("Coś poszło nie tak...");
    })
  }

  onSubmit() {
    this.getInvoicesBetweenDates();
  }
}
