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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<AgeDiscountsRequest> getAgeDiscountsRequest() {
        return ageDiscountsResponse;
    }

    public void setAgeDiscountsResponse(List<AgeDiscountsRequest> ageDiscountsResponse) {
        this.ageDiscountsResponse = ageDiscountsResponse;
    }

    public List<QuantityPassRequest> getQuantityPassRequest() {
        return quantityPassResponse;
    }

    public void setQuantityPassResponse(List<QuantityPassRequest> quantityPassResponse) {
        this.quantityPassResponse = quantityPassResponse;
    }

    public List<TimePassRequest> getTimePassRequest() {
        return timePassResponse;
    }

    public void setTimePassResponse(List<TimePassRequest> timePassResponse) {
        this.timePassResponse = timePassResponse;
    }
}
