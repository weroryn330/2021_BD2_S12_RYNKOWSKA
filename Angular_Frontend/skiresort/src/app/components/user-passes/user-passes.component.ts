import {Component, OnInit} from '@angular/core';
import {PassService} from "../../services/pass.service";
import * as fileSaver from 'file-saver';
import {PassResponse} from "../../classes/pass-response";

@Component({
  selector: 'app-user-passes',
  templateUrl: './user-passes.component.html',
  styleUrls: ['./user-passes.component.css']
})
export class UserPassesComponent implements OnInit {
  passesList: PassResponse[] = [];

  constructor(private passService: PassService) {
  }

  ngOnInit(): void {
    this.getUserPasses();
  }


  private getUserPasses() {
    this.passService.getUserPasses().subscribe(data => {
        data.map(pass => this.passesList.push(new PassResponse(pass.idPass, pass.unitPrice, pass.firstName,
          pass.lastName, pass.startDate, pass.endDate, pass.birthDate, pass.usesTotal, pass.usesLeft))
        )
      this.passesList.sort(function(x,y){ return (y.isActive? 1 : 0)-(x.isActive? 1 : 0)});
        console.log(data);
      },
      error => {
        alert("Brak karnetów");
        this.passesList = [];
      })
  }

  downloadQR(passId: number) {
    this.passService.getQR(passId).subscribe((data: any) => {
      let blob: any = new Blob([data], {type: 'text/json'});
      fileSaver.saveAs(blob, 'karnet' + passId + '.jpg');
    }, error => {
      alert("Coś poszło nie tak...");
    })
  }
}
