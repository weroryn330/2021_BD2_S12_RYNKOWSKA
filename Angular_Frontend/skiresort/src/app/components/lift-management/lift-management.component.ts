import { Component, OnInit } from '@angular/core';
import {SkiliftService} from "../../services/skilift.service";

@Component({
  selector: 'app-lift-management',
  templateUrl: './lift-management.component.html',
  styleUrls: ['./lift-management.component.css']
})
export class LiftManagementComponent implements OnInit {
skiliftsList: any;

  constructor(private skiliftService: SkiliftService) { }

  ngOnInit(): void {
    this.getSkilifts();
  }

  private getSkilifts() {
    this.skiliftService.getTechnicalSkilifts().subscribe((data: any) => {
        this.skiliftsList = data;
        console.log(data);
      },
      error => {
        alert("Coś poszło nie tak...");
      })
  }

  changeSkiliftState(idSkiLift: number, name: string): any{
    const element = window.document.getElementById(name) as HTMLInputElement;
    this.skiliftService.changeSkiliftState(idSkiLift).subscribe((data: any) => {
        console.log(data);
        element.checked = data.isOpened;
        return data.isOpened;
      },
      error => {
        alert("Coś poszło nie tak...");
        element.checked = false;
        return false;
      })
  }
}
