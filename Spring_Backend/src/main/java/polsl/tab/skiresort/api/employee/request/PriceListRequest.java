package polsl.tab.skiresort.api.employee.request;

import java.sql.Date;
import java.util.List;

public class PriceListRequest {

    private Date startDate;

    private Date endDate;

    private List<AgeDiscountsRequest> ageDiscountsRequest;

    private List<QuantityPassRequest> quantityPassRequest;

    private List<TimePassRequest> timePassRequest;

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<AgeDiscountsRequest> getAgeDiscountsRequest() {
        return ageDiscountsRequest;
    }

    public List<QuantityPassRequest> getQuantityPassRequest() {
        return quantityPassRequest;
    }

    public List<TimePassRequest> getTimePassRequest() {
        return timePassRequest;
    }
}
