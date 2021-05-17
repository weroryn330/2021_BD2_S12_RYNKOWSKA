package polsl.tab.skiresort.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "age_discounts")
public class AgeDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idAgeDiscount;

    @NotBlank(message = "Your age discount age min should not be empty!")
    private Integer ageMin;

    @NotBlank(message = "Your age discount age max should not be empty!")
    private Integer ageMax;

    @NotBlank(message = "Your age percentage should not be empty!")
    private Integer percentage;

    @ManyToOne
    @JoinColumn(name = "price_list_id_price_list")
    private PriceList priceListIdPriceList;

    public Integer getIdAgeDiscounts() {
        return idAgeDiscount;
    }

    void setIdAgeDiscounts(Integer idAgeDiscount) {
        this.idAgeDiscount = idAgeDiscount;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public PriceList getPriceListIdPriceList() {
        return priceListIdPriceList;
    }

    public void setPriceListIdPriceList(PriceList priceListIdPriceList) {
        this.priceListIdPriceList = priceListIdPriceList;
    }
}
