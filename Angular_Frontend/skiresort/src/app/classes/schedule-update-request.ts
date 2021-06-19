export class ScheduleUpdateRequest {
  opensTime: string;
  closesTime: string;
  skiLiftId: number;
  skiLiftScheduleId: number;


  constructor(opensTime: string, closesTime: string, skiLiftId: number, skiLiftScheduleId: number) {
    this.opensTime = opensTime;
    this.closesTime = closesTime;
    this.skiLiftId = skiLiftId;
    this.skiLiftScheduleId = skiLiftScheduleId;
  }
}
