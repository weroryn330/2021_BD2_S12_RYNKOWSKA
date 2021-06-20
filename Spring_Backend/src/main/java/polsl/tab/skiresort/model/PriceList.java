package polsl.tab.skiresort.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "price_lists")
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPriceList;

    @NotNull(message = "Your price list start date should not be empty!")
    private Date startDate;

    @NotNull(message = "Your price list end date should not be empty!")
    private Date endDate;

    @OneToMany(mappedBy = "priceList")
    private List<Pass> passList;

    @OneToMany(mappedBy = "priceListIdPriceList",cascade = CascadeType.PERSIST)
    private List<AgeDiscount> ageDiscountList;

    @OneToMany(mappedBy = "priceListIdPriceList",cascade = CascadeType.PERSIST)
    private List<TimePass> timePassList;

    @OneToMany(mappedBy = "priceListIdPriceList",cascade = CascadeType.PERSIST)
    private List<QuantityPass> quantityPassList;

    public PriceList() {}

    public PriceList(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //For tests
    public PriceList(Integer idPriceList ,Date startDate, Date endDate) {
        this.idPriceList = idPriceList;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PriceList(Date startDate, Date endDate, List<AgeDiscount> ageDiscountList, List<TimePass> timePassList, List<QuantityPass> quantityPassList)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.ageDiscountList = ageDiscountList;
        this.timePassList = timePassList;
        this.quantityPassList = quantityPassList;
    }

    public Integer getIdPriceList() {
        return idPriceList;
    }

    void setIdPriceList(Integer idPriceList) {
        this.idPriceList = idPriceList;
    }

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

    public List<Pass> getPassList() {
        return passList;
    }

    public void setPassList(List<Pass> passList) {
        this.passList = passList;
    }

    public List<AgeDiscount> getAgeDiscountList() {
        return ageDiscountList;
    }

    public void setAgeDiscountList(List<AgeDiscount> ageDiscountList) {
        this.ageDiscountList = ageDiscountList;
    }

    public List<TimePass> getTimePassList() {
        return timePassList;
    }

    public void setTimePassList(List<TimePass> timePassList) {
        this.timePassList = timePassList;
    }

    public List<QuantityPass> getQuantityPassList() {
        return quantityPassList;
    }

    public void setQuantityPassList(List<QuantityPass> quantityPassList) {
        this.quantityPassList = quantityPassList;
    }
}
