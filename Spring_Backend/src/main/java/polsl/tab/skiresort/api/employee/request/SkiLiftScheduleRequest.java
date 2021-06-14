package polsl.tab.skiresort.api.employee.request;

import java.sql.Date;
import java.sql.Time;

public class SkiLiftScheduleRequest {

    private Date startDate;

    private Date endDate;

    private Time opensTime;

    private Time closesTime;

    private Integer skiLiftId;

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
