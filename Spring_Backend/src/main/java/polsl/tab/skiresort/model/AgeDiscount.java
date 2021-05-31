package polsl.tab.skiresort.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "age_discounts")
public class AgeDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAgeDiscount;

    @NotNull(message = "Your age discount age min should not be empty!")
    private Integer ageMin;

    @NotNull(message = "Your age discount age max should not be empty!")
    private Integer ageMax;

    @NotNull(message = "Your age percentage should not be empty!")
    private Integer percentage;

    @ManyToOne
    @JoinColumn(name = "price_list_id_price_list")
    private PriceList priceListIdPriceList;

    public AgeDiscount(){};

    public AgeDiscount(Integer ageMin,Integer ageMax, Integer percentage)
    {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.percentage = percentage;
    }
    public AgeDiscount(Integer ageMin,Integer ageMax, Integer percentage,PriceList priceListIdPriceList)
    {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.percentage = percentage;
        this.priceListIdPriceList = priceListIdPriceList;
    }

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
