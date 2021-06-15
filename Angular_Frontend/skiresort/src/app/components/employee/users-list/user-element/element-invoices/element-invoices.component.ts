import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PassRequest} from "../../../../../classes/pass-request";
import * as fileSaver from "file-saver";
import {InvoiceService} from "../../../../../services/invoice.service";

@Component({
  selector: 'app-element-invoices',
  templateUrl: './element-invoices.component.html',
  styleUrls: ['./element-invoices.component.css']
})
export class ElementInvoicesComponent implements OnInit {

  @Input() userEmail: any;
  @Output() newCloseComponentEvent = new EventEmitter<number>();
  invoicesList: any;
  page = 1;
  constructor(private invoiceService: InvoiceService) { }

  ngOnInit(): void {
    this.getUserInvoices();
  }
  closeInvoices(){
    this.newCloseComponentEvent.emit(0);
  }

  downloadInvoicePDF(invoiceId: number, invoiceDate: string) {
    this.invoiceService.getUserInvoicePDF(invoiceId).subscribe((data: any) => {
      let blob: any = new Blob([data], {type: 'application/pdf'});
      fileSaver.saveAs(blob, 'faktura_' + invoiceId + '_' + invoiceDate + '.pdf');
    }, error => {
      alert("Wystąpił problem z pobraniem pliku");
    })
  }

  private getUserInvoices() {
    this.invoiceService.getUserInvoicesForOwner(this.userEmail).subscribe((data: any) => {
        this.invoicesList = data;
        console.log(data);
      },
      error => {
        alert("Nie znaleziono faktur");
        this.invoicesList = [];
      })
  }
}
