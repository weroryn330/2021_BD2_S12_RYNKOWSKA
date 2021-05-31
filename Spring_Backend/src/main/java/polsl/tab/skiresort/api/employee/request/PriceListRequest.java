package polsl.tab.skiresort.api.employee.request;

import java.sql.Date;
import java.util.List;

public class PriceListRequest {

    private Date startDate;

    private Date endDate;

    private List<AgeDiscountsRequest> ageDiscountsResponse;

    private List<QuantityPassRequest> quantityPassResponse;

    private List<TimePassRequest> timePassResponse;

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<AgeDiscountsRequest> getAgeDiscountsRequest() {
        return ageDiscountsResponse;
    }

    public List<QuantityPassRequest> getQuantityPassRequest() {
        return quantityPassResponse;
    }

    public List<TimePassRequest> getTimePassRequest() {
        return timePassResponse;
    }
}
