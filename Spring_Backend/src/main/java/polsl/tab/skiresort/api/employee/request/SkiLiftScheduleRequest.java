package polsl.tab.skiresort.api.employee.request;

import java.sql.Time;

public class SkiLiftScheduleRequest {

    private Time opensTime;

    private Time closesTime;

    private Integer skiLiftId;

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
