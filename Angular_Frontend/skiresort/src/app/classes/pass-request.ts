import {formatDate} from "@angular/common";

export class PassRequest {
  unitPrice: number;
  firstName: string;
  lastName: string ;
  startDate: string;
  endDate: string;
  birthDate: string;
  usesTotal: number;


  constructor(unitPrice: number, firstName: string, lastName: string, startDate: Date, endDate: Date, birthDate: Date, usesTotal: number) {
    this.unitPrice = unitPrice;
    this.firstName = firstName;
    this.lastName = lastName;
    this.startDate = formatDate(startDate,'yyyy-MM-dd\'T\'HH:mm:ss.SSS', 'en-US');
    this.endDate = formatDate(endDate,'yyyy-MM-dd\'T\'HH:mm:ss.SSS', 'en-US');
    this.birthDate = formatDate(birthDate,'yyyy-MM-dd', 'en-US');
    this.usesTotal = usesTotal;
  }
}
