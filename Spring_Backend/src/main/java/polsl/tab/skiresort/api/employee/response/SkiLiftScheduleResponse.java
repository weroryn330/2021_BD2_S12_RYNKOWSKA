package polsl.tab.skiresort.api.employee.response;

import polsl.tab.skiresort.model.SkiLiftSchedule;

import java.sql.Date;
import java.sql.Time;

public class SkiLiftScheduleResponse {

    private Time opensTime;

    private Time closesTime;

    private Integer skiLiftId;

    private Integer skiLiftScheduleId;

    private String skiLiftName;

    public SkiLiftScheduleResponse(SkiLiftSchedule s){
        this.opensTime = s.getOpensTime();
        this.closesTime = s.getClosesTime();
        this.skiLiftScheduleId = s.getIdSkiLiftSchedule();
        this.skiLiftId = s.getSkiLiftIdSkiLift().getIdSkiLift();
        this.skiLiftName = s.getSkiLiftIdSkiLift().getName();
    }

    public String getSkiLiftName() {
        return skiLiftName;
    }

    public void setSkiLiftName(String skiLiftName) {
        this.skiLiftName = skiLiftName;
    }

    public Integer getSkiLiftScheduleId() {
        return skiLiftScheduleId;
    }

    public void setSkiLiftScheduleId(Integer skiLiftScheduleId) {
        this.skiLiftScheduleId = skiLiftScheduleId;
    }

    public Time getOpensTime() {
        return opensTime;
    }

    public void setOpensTime(Time opensTime) {
        this.opensTime = opensTime;
    }

    public Time getClosesTime() {
        return closesTime;
    }

    public void setClosesTime(Time closesTime) {
        this.closesTime = closesTime;
    }

    public Integer getSkiLiftId() {
        return skiLiftId;
    }

    public void setSkiLiftId(Integer skiLiftId) {
        this.skiLiftId = skiLiftId;
    }
}
