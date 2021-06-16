package polsl.tab.skiresort.api.employee.response;

import polsl.tab.skiresort.model.SkiLiftSchedule;

import java.sql.Date;
import java.sql.Time;

public class SkiLiftScheduleResponse {

    private Date startDate;

    private Date endDate;

    private Time opensTime;

    private Time closesTime;

    private Integer skiLiftId;

    private Integer skiLiftScheduleId;

    public SkiLiftScheduleResponse(SkiLiftSchedule s){
        this.startDate = s.getStartDate();
        this.endDate = s.getEndDate();
        this.opensTime = s.getOpensTime();
        this.closesTime = s.getClosesTime();
        this.skiLiftId = s.getSkiLiftIdSkiLift().getIdSkiLift();
    }

    public Integer getSkiLiftScheduleId() {
        return skiLiftScheduleId;
    }

    public void setSkiLiftScheduleId(Integer skiLiftScheduleId) {
        this.skiLiftScheduleId = skiLiftScheduleId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
