package polsl.tab.skiresort.api.employee.request;

import polsl.tab.skiresort.model.SkiLiftSchedule;

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

    public Date getEndDate() {
        return endDate;
    }

    public Time getOpensTime() {
        return opensTime;
    }

    public Time getClosesTime() {
        return closesTime;
    }

    public Integer getSkiLiftId() {
        return skiLiftId;
    }
}
