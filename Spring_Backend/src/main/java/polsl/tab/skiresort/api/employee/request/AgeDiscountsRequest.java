package polsl.tab.skiresort.api.employee.request;

public class AgeDiscountsRequest {

    private Integer ageMin;

    private Integer ageMax;

    private Integer percentage;

    public Integer getAgeMin() {
        return ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public Integer getPercentage() {
        return percentage;
    }
}
