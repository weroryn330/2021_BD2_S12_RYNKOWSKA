package polsl.tab.skiresort.model;

import polsl.tab.skiresort.model.PriceList;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quantity_passes")
public class QuantityPass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idQuantityPass;

    @NotNull(message = "Your quantity pass quantity should not be empty!")
    private Integer quantity;

    @NotNull(message = "Your quantity pass price should not be empty!")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "price_list_id_price_list")
    private PriceList priceListIdPriceList;

    public QuantityPass(){};

    public QuantityPass(Integer quantity, Float price)
    {
        this.quantity = quantity;
        this.price = price;
    }

    public QuantityPass(Integer quantity, Float price, PriceList priceList)
    {
        this.quantity = quantity;
        this.price = price;
        this.priceListIdPriceList = priceList;
    }

    public Integer getIdQuantityPass() {
        return idQuantityPass;
    }

    void setIdQuantityPass(Integer idQuantityPass) {
        this.idQuantityPass = idQuantityPass;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public PriceList getPriceListIdPriceList() {
        return priceListIdPriceList;
    }

    public void setPriceListIdPriceList(PriceList priceListIdPriceList) {
        this.priceListIdPriceList = priceListIdPriceList;
    }
}
