export class PassRequest {
  firstName: string = '';
  lastName: string = '';
  birthDate: Date = {} as Date;
  usesTotal: number = {} as number;
  startDate: Date = {} as Date;
  hours: number = {} as number;
  unitPrice: number = {} as number;


  constructor() {
  }

  setAtributes(firstName: string, lastName: string, birthDate: Date, usesTotal: number, startDate: Date, hours: number, unitPrice: number){
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.usesTotal = usesTotal;
    this.startDate = startDate;
    this.hours = hours;
    this.unitPrice = unitPrice;
  }
}
