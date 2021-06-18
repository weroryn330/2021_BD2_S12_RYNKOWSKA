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
        data.map(pass => this.passesList.push(new PassResponse(pass.id, pass.unitPrice, pass.firstName,
          pass.lastName, pass.startDate, pass.endDate, pass.birthDate, pass.usesTotal, pass.usesLeft, pass.blocked))
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

  downloadQR(id: number) {
    this.passService.getQR(id).subscribe((data: any) => {
      let blob: any = new Blob([data], {type: 'text/json'});
      fileSaver.saveAs(blob, 'karnet' + id + '.jpg');
    }, error => {
      alert("Coś poszło nie tak...");
    })
  }

  refundPass(id: number, index: number) {
    this.passService.refundPass(id).subscribe((data: any) => {
      alert("Pomyślny zwrot karnetu");
      this.passesList.splice(index, 1);
    }, error => {
      alert("Niepomyślny zwrot karnetu");
    })
  }

  canBeRefunded(pass: PassResponse): boolean {
    if(pass.startDate && pass.endDate){
      return (new Date(pass.startDate).getTime() > Date.now()) && !pass.blocked;
    }
    else if(pass.usesTotal){
      return (pass.usesTotal === pass.usesLeft) && !pass.blocked;
    }
    return false;
  }
}
