package polsl.tab.skiresort.api.employee.response;

import polsl.tab.skiresort.model.TimePass;

public class TimePassResponse {
    private final Integer hours;

    private final Float price;

    public TimePassResponse(TimePass timePass) {
        this.hours = timePass.getHours();
        this.price = timePass.getPrice();
    }

    public TimePassResponse(Integer hours, Float price) {
        this.hours = hours;
        this.price = price;
    }

    public Integer getHours() {
        return hours;
    }

    public Float getPrice() {
        return price;
    }
}
