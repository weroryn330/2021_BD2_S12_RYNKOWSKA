package polsl.tab.skiresort.pricelist;

import polsl.tab.skiresort.pass.Pass;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "price_list")
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPriceList;

    @NotBlank(message = "Your start date should not be empty!")
    private Date startDate;

    @NotBlank(message = "Your end date should not be empty!")
    private Date endDate;

    @NotBlank(message = "Your percentage should not be empty!")
    private Integer percentage;

    @OneToOne()
    @JoinColumn(name = "pass_id_invoice_item")
    private Pass pasIdInvoiceItem;

    public Integer getIdPriceList() {
        return idPriceList;
    }

    public void setIdPriceList(Integer idPriceList) {
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

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Pass getPasIdInvoiceItem() {
        return pasIdInvoiceItem;
    }

    public void setPasIdInvoiceItem(Pass pasIdInvoiceItem) {
        this.pasIdInvoiceItem = pasIdInvoiceItem;
    }
}

