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

  constructor(private invoiceService: InvoiceService) {
  }

  ngOnInit(): void {
    this.getInvoices();
  }

  private getInvoices() {
    this.invoiceService.getInvoices().subscribe((data: any) => {
        this.invoicesList = data;
        console.log(data);
      },
      error => {
        alert("Coś poszło nie tak...");
      })
  }

  downloadInvoicePDF(invoiceId: number, invoiceDate: string) {
    this.invoiceService.getUserInvoicePDF(invoiceId).subscribe((data: any) => {
      let blob: any = new Blob([data], {type: 'application/pdf'});
      fileSaver.saveAs(blob, 'faktura_' + invoiceId + '_' + invoiceDate + '.pdf');
    }, error => {
      alert("Coś poszło nie tak...");
    })
  }
}
