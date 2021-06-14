package polsl.tab.skiresort.api.mockup_usage.response;

import polsl.tab.skiresort.model.SkiLiftSchedule;

import java.sql.Time;

public class SkiLiftMockupScheduleResponse {

    private final Time opensTime;

    private final Time closesTime;

    public SkiLiftMockupScheduleResponse(SkiLiftSchedule skiLiftSchedule) {
        this.opensTime = skiLiftSchedule.getOpensTime();
        this.closesTime = skiLiftSchedule.getClosesTime();
    }

    public Time getOpensTime() {
        return opensTime;
    }

    public Time getClosesTime() {
        return closesTime;
    }
}
