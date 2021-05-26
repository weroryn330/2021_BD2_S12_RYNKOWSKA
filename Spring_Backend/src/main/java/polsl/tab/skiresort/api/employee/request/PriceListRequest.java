package polsl.tab.skiresort.api.employee.request;

import java.util.Date;

public class PriceListRequest {

    private Date startDate;

    private Date endDate;

    private AgeDiscountsRequest ageDiscountsResponse;

    private QuantityPassRequest quantityPassResponse;

    private TimePassRequest timePassResponse;

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public AgeDiscountsRequest getAgeDiscountsRequest() {
        return ageDiscountsResponse;
    }

    public QuantityPassRequest getQuantityPassRequest() {
        return quantityPassResponse;
    }

    public TimePassRequest getTimePassRequest() {
        return timePassResponse;
    }
}
