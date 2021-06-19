export class SkiliftResponse {
  name: string;
  height: number;
  isOpened: boolean;
  peopleUsingLift: number;
  opensTime: string;
  closesTime: string;


  constructor(name: string, height: number, isOpened: boolean, peopleUsingLift: number, opensTime: string, closesTime: string) {
    this.name = name;
    this.height = height;
    this.isOpened = isOpened;
    this.peopleUsingLift = peopleUsingLift;
    this.opensTime = opensTime;
    this.closesTime = closesTime;
  }
}
