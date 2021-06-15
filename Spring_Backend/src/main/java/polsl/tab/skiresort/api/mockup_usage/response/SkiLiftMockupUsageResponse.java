package polsl.tab.skiresort.api.mockup_usage.response;

import polsl.tab.skiresort.model.SkiLift;

public class SkiLiftMockupUsageResponse {

    private final String name;

    private final Integer height;

    private final Boolean isOpened;

    /**
     * People on ski lift
     */
    private final Integer peopleUsingLift;

    /**
     * Current ski lift schedule
     */
    private final SkiLiftMockupScheduleResponse skiLiftMockupScheduleResponse;

    public SkiLiftMockupUsageResponse(
            SkiLift skiLift,
            Integer peopleUsingLift,
            SkiLiftMockupScheduleResponse skiLiftMockupScheduleResponse
    ) {
        this.name = skiLift.getName();
        this.height = skiLift.getHeight();
        this.isOpened = skiLift.getIsOpened();
        this.peopleUsingLift = peopleUsingLift;
        this.skiLiftMockupScheduleResponse = skiLiftMockupScheduleResponse;
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Boolean getOpened() {
        return isOpened;
    }

    public Integer getPeopleUsingLift() {
        return peopleUsingLift;
    }

    public SkiLiftMockupScheduleResponse getSkiLiftScheduleResponse() {
        return skiLiftMockupScheduleResponse;
    }
}
