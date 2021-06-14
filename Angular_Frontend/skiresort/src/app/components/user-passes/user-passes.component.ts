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
  page = 1;

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
        this.passesList.sort(function (x, y) {
          return (y.isActive ? 1 : 0) - (x.isActive ? 1 : 0)
        });
        console.log(data);
      },
      error => {
        alert("Brak karnetów");
        this.passesList = [];
      })
  }

  downloadQR(idPass: number) {
    this.passService.getQR(idPass).subscribe((data: any) => {
      let blob: any = new Blob([data], {type: 'text/json'});
      fileSaver.saveAs(blob, 'karnet' + idPass + '.jpg');
    }, error => {
      alert("Coś poszło nie tak...");
    })
  }

  refundPass(idPass: number) {
    this.passService.refundPass(idPass).subscribe((data: any) => {
      alert("Pomyślny zwrot karnetu");
    }, error => {
      alert("Niepomyślny zwrot karnetu");
    })
  }

  canBeRefunded(pass: PassResponse): boolean {
    if(pass.startDate && pass.endDate){
      return new Date(pass.startDate).getTime() > Date.now();
    }
    else if(pass.usesTotal){
      return pass.usesTotal === pass.usesLeft;
    }
    return false;
  }
}
