export class AgeDiscountResponse {

  ageMin: number;
  ageMax: number;
  percentage: number;

  constructor(ageMin: number, ageMax: number, percentage: number) {
    this.ageMin = ageMin;
    this.ageMax = ageMax;
    this.percentage = percentage;
  }
}
