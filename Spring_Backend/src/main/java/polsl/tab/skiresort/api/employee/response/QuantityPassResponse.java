package polsl.tab.skiresort.api.employee.response;

import polsl.tab.skiresort.model.QuantityPass;

public class QuantityPassResponse {
    private final Integer quantity;

    private final Float price;

    public QuantityPassResponse(QuantityPass quantityPass) {
        this.quantity = quantityPass.getQuantity();
        this.price = quantityPass.getPrice();
    }

    public QuantityPassResponse(Integer quantity, Float price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getPrice() {
        return price;
    }
}
