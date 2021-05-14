package polsl.tab.skiresort.timepass;

import polsl.tab.skiresort.pricelist.PriceList;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "time_passes")
public class TimePass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idTimePass;

    @NotBlank(message = "Your time pass hours should not be empty!")
    private Integer hours;

    @NotBlank(message = "Your time pass price should not be empty!")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "price_list_id_price_list")
    @NotNull
    private PriceList priceListIdPriceList;

    public Integer getIdTimePass() {
        return idTimePass;
    }

    public void setIdTimePass(Integer idTimePass) {
        this.idTimePass = idTimePass;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
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
