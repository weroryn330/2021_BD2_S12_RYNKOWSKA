import { Component, OnInit } from '@angular/core';
import {PassService} from "../../../services/pass.service";

@Component({
  selector: 'app-active-passes',
  templateUrl: './active-passes.component.html',
  styleUrls: ['./active-passes.component.css']
})
export class ActivePassesComponent implements OnInit {
  activePasses: any;
  constructor(private passService: PassService) { }

  ngOnInit(): void {
    this.loadActivePasses();
  }

  private loadActivePasses() {
  this.passService.getActivePasses().subscribe(data => {
      this.activePasses = data;
      },
      error => {
        alert("Coś poszło nie tak...");
      })
  }
}
