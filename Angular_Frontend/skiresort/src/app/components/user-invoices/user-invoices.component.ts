import {Component, OnInit} from '@angular/core';
import {InvoiceService} from "../../services/invoice.service";
import * as fileSaver from "file-saver";

@Component({
  selector: 'app-user-invoices',
  templateUrl: './user-invoices.component.html',
  styleUrls: ['./user-invoices.component.css']
})
export class UserInvoicesComponent implements OnInit {
  invoicesList: any;
  page = 1;

  constructor(private invoiceService: InvoiceService) {
  }

  ngOnInit(): void {
    this.getUserInvoices();
  }

  private getUserInvoices() {
    this.invoiceService.getUserInvoices().subscribe((data: any) => {
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
