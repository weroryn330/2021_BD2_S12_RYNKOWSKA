export class ScheduleUpdateRequest {
  opensTime: string;
  closesTime: string;
  skiLiftId: number;


  constructor(opensTime: string, closesTime: string, skiLiftId: number) {
    this.opensTime = opensTime;
    this.closesTime = closesTime;
    this.skiLiftId = skiLiftId;
  }
}
