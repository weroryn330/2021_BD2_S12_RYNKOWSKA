package polsl.tab.skiresort.api.employee.response;

import polsl.tab.skiresort.model.AgeDiscount;

public class AgeDiscountsResponse {
    private final Integer ageMin;

    private final Integer ageMax;

    private final Integer percentage;

    public AgeDiscountsResponse(AgeDiscount ageDiscount) {
        this.ageMax = ageDiscount.getAgeMax();
        this.ageMin = ageDiscount.getAgeMin();
        this.percentage = ageDiscount.getPercentage();
    }

    public AgeDiscountsResponse(Integer ageMin,
                                Integer ageMax,
                                Integer percentage) {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.percentage = percentage;
    }

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
