export class PassResponse {
  id: number;
  unitPrice: number;
  firstName: string;
  lastName: string ;
  startDate: any;
  endDate: any;
  birthDate: string;
  usesTotal: number;
  usesLeft: number;
  blocked: boolean = false;
  isActive: boolean;



  constructor(id: number, unitPrice: number, firstName: string, lastName: string, startDate: any, endDate: any, birthDate: string, usesTotal: number, usesLeft: number) {
    this.id = id;
    this.unitPrice = unitPrice;
    this.firstName = firstName;
    this.lastName = lastName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.birthDate = birthDate;
    this.usesTotal = usesTotal;
    this.usesLeft = usesLeft;
    if(startDate || endDate){
      this.isActive = (new Date() < new Date(endDate)) ? true : false;
    }
    else{
      this.isActive = usesLeft>0 ? true : false;
    }
  }

  checkIfActive() {

  }
}
