import { Component, OnInit } from '@angular/core';
import {PassService} from "../../../services/pass.service";
import {PassResponse} from "../../../classes/pass-response";

@Component({
  selector: 'app-active-passes',
  templateUrl: './active-passes.component.html',
  styleUrls: ['./active-passes.component.css']
})
export class ActivePassesComponent implements OnInit {
  activePasses: PassResponse[] = [];
  page = 1;
  constructor(private passService: PassService) { }

  ngOnInit(): void {
    this.loadActivePasses();
  }

  private loadActivePasses() {
  this.passService.getActivePasses().subscribe(data => {
    console.log(data);
      data.map((pass: any) => this.activePasses.push(new PassResponse(pass.idPass, pass.unitPrice, pass.firstName,
        pass.lastName, pass.startDate, pass.endDate, pass.birthDate, pass.usesTotal, pass.usesLeft))
      )
      this.activePasses.sort(function (x, y) {
        return x.lastName.localeCompare(y.lastName);
      });
      },
      error => {
        this.activePasses = [];
        alert("Brak aktywnych karnetów do wyświetlenia");
      })
  }

  changeBlockage(idPass: number) {
    console.log(idPass);
    this.passService.changeBlockage(idPass).subscribe(data => {
        alert("Blokada karnetu została zmieniona");
      },
      error => {
        alert("Nie udało się zmienić ustawienia blokady karnetu");
      })
  }
}
