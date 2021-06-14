package polsl.tab.skiresort.api.employee.response;

import java.sql.Date;
import java.sql.Time;

public class SkiLiftScheduleResponse {

    private Date startDate;

    private Date endDate;

    private Time opensTime;

    private Time closesTime;

    private Integer skiLiftId;

    private Integer skiLiftScheduleId;

    public SkiLiftScheduleResponse(Date startDate, Date endDate, Time opensTime, Time closesTime, Integer skiLiftId, Integer skiLiftScheduleId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.opensTime = opensTime;
        this.closesTime = closesTime;
        this.skiLiftId = skiLiftId;
        this.skiLiftScheduleId = skiLiftScheduleId;
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