package polsl.tab.skiresort.api.technical_employee.response;

import polsl.tab.skiresort.model.SkiLift;

public class SkiLiftResponse {

    private final Integer idSkiLift;

    private final String name;

    private final Integer height;

    private final Character isOpened;

    public SkiLiftResponse(SkiLift skiLift) {
        this.idSkiLift = skiLift.getIdSkiLift();
        this.name = skiLift.getName();
        this.height = skiLift.getHeight();
        this.isOpened = skiLift.getIsOpened();
    }

    public Integer getIdSkiLift() {
        return idSkiLift;
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Character getIsOpened() {
        return isOpened;
    }
}
