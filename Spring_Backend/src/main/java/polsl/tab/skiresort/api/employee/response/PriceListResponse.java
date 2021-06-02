package polsl.tab.skiresort.api.employee.response;

import java.util.List;

public class PriceListResponse {

    private final Integer id;

    private List<AgeDiscountsResponse> ageDiscountsResponse;

    private List<QuantityPassResponse> quantityPassResponse;

    private List<TimePassResponse> timePassResponse;

    public PriceListResponse(Integer id,
                             List<AgeDiscountsResponse> ageDiscountsResponse,
                             List<QuantityPassResponse> quantityPassResponse,
                             List<TimePassResponse> timePassResponse
    ) {
        this.id = id;
        this.ageDiscountsResponse = ageDiscountsResponse;
        this.quantityPassResponse = quantityPassResponse;
        this.timePassResponse = timePassResponse;
    }

    public Integer getId() {
        return id;
    }

    public List<AgeDiscountsResponse> getAgeDiscountsResponse() {
        return ageDiscountsResponse;
    }

    public List<QuantityPassResponse> getQuantityPassResponse() {
        return quantityPassResponse;
    }

    public List<TimePassResponse> getTimePassResponse() {
        return timePassResponse;
    }

    public void setAgeDiscountsResponse(List<AgeDiscountsResponse> ageDiscountsResponse) {
        this.ageDiscountsResponse = ageDiscountsResponse;
    }

    public void setQuantityPassResponse(List<QuantityPassResponse> quantityPassResponse) {
        this.quantityPassResponse = quantityPassResponse;
    }

    public void setTimePassResponse(List<TimePassResponse> timePassResponse) {
        this.timePassResponse = timePassResponse;
    }
}
